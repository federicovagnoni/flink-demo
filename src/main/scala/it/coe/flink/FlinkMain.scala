package it.coe.flink

import com.google.gson.Gson
import it.coe.common.Utils.VACCINE_SOMMINISTRATION_TOPIC
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

object FlinkMain {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = FlinkKafkaProperties.getProperties
    val stream = env.addSource(
      new FlinkKafkaConsumer[String](VACCINE_SOMMINISTRATION_TOPIC, new SimpleStringSchema(), properties)
        .setStartFromEarliest()
    )


    val httpHosts = new java.util.ArrayList[HttpHost]
    httpHosts.add(new HttpHost("127.0.0.1", 9200, "http"))

    val esSinkBuilder = new ElasticsearchSink.Builder[String](
      httpHosts,
      new ElasticsearchSinkFunction[String] {
        def process(element: String, ctx: RuntimeContext, indexer: RequestIndexer) {
//         val json = MapModelToJson.modelToJson(element)
          val gson = new Gson

          val json = gson.fromJson(element, (new java.util.HashMap[String, Any]()).getClass)
          println(json)
          val rqst: IndexRequest = Requests.indexRequest
            .index("somministrazioni-vaccini")
            .source(json)

          indexer.add(rqst)
        }
      }
    )

    // configuration for the bulk requests; this instructs the sink to emit after every element, otherwise they would be buffered
    esSinkBuilder.setBulkFlushMaxActions(1)

    stream.addSink(esSinkBuilder.build)

    env.execute
  }

}
