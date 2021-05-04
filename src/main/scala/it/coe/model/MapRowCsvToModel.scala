package it.coe.model

import java.time.LocalDate
import scala.io.Source

object MapRowCsvToModel {
  def rowToObject(row: String): VaccineSomministration = {
    val x = row.split(",", -1)
    VaccineSomministration(
      LocalDate.parse(x(0)),
      x(1),
      x(2),
      x(3),
      x(4).toInt,
      x(5).toInt,
      x(6).toInt,
      x(7).toInt,
      x(8).toInt,
      x(9).toInt,
      x(10).toInt,
      x(11).toInt,
      x(12).toInt,
      x(13).toInt,
      x(14).toInt,
      x(15).toInt,
      x(16).toInt,
      x(17).toInt,
      x(18),
      x(19),
      x(20).toInt,
      x(21)
    )
  }


  def readCSV() = {
    val path = getClass.getResourceAsStream("/somministrazioni-vaccini-latest.csv")
    val lines = Source.fromInputStream(path).getLines
    lines.map(rowToObject)
  }

}
