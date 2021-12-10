package eu.throup.aoc.year2020.day16

import scala.language.postfixOps

case class Field(private val input: String) {
  private val Pattern =
    "^([\\w\\s]+):\\s*(\\d+)-(\\d+)\\s*or\\s*(\\d+)-(\\d+)$" r
  private val matches = Pattern.findFirstMatchIn(input).get

  val label: String = matches.group(1)
  private val r1a: Long = matches.group(2).toLong
  private val r1b: Long = matches.group(3).toLong
  private val r2a: Long = matches.group(4).toLong
  private val r2b: Long = matches.group(5).toLong

  val range: Seq[Long] = (r1a to r1b) ++ (r2a to r2b)
}
