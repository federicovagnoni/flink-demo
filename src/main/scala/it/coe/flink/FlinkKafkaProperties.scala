package it.coe.flink

import it.coe.common.Utils.VACCINE_SOMMINISTRATION_GROUP

import java.util.Properties

object FlinkKafkaProperties {
  var properties: Option[Properties] = None

  def getProperties: Properties ={
    properties.getOrElse(createProperties)
  }

  private def createProperties: Properties = {
    properties = Some(new Properties())
    properties.get.setProperty("bootstrap.servers", "localhost:9092")
    properties.get.setProperty("group.id", VACCINE_SOMMINISTRATION_GROUP)
    properties.get
  }
}
