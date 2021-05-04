package it.coe.kafka

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
    for(i <- 1 to 100) {
      val record = new ProducerRecord[String, String](topic, "key", s"value $i")
      producer.send(record)
    }
    producer.close()
  }
}
