package it.coe.kafka

import com.google.gson.Gson
import it.coe.common.Utils.VACCINE_SOMMINISTRATION_TOPIC
import it.coe.model.MapRowCsvToModel
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object Producer {

  def main(args: Array[String]): Unit = {
    writeToKafka(VACCINE_SOMMINISTRATION_TOPIC)
  }

  def writeToKafka(topic: String): Unit = {
    val props = KafkaProperties.getProperties

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
