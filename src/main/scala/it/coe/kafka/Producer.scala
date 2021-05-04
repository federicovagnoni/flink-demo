package it.coe.kafka

import com.google.gson.Gson
import it.coe.model.VaccineSomministration
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties
import scala.util.Random

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
    //TODO add readCSv
    for(i <- 1 to 100) {
      val gson = new Gson
      val record = new ProducerRecord[String, String](topic, "key", gson.toJson(VaccineSomministration))
      producer.send(record)
    }
    producer.close()
  }
}
