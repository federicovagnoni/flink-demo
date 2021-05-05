package it.coe.flink

import it.coe.common.Utils.{VACCINE_SOMMINISTRATION_GROUP, VACCINE_SOMMINISTRATION_TOPIC}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

import java.util.Properties

object FlinkMain {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = FlinkKafkaProperties.getProperties
    val stream = env.addSource(
      new FlinkKafkaConsumer[String](VACCINE_SOMMINISTRATION_TOPIC, new SimpleStringSchema(), properties)
        .setStartFromEarliest()
    )

    stream.print
    env.execute
  }

}
