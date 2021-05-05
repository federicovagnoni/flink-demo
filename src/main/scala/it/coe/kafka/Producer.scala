package it.coe.kafka

import com.google.gson.Gson
import it.coe.model.MapRowCsvToModel
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object Producer {

  def main(args: Array[String]): Unit = {
    writeToKafka("test")
  }

  def writeToKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val objects = MapRowCsvToModel.readCSV()
    objects.foreach(x => {
      val gson = new Gson
      val record = new ProducerRecord[String, String](topic, "key", gson.toJson(x))
      producer.send(record)
    }
    )
    producer.close()
  }
}
