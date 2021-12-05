package eu.throup.aoc.year2020.day06

import eu.throup.aoc.DayXX

object Day06 extends DayXX {
  override def part1(input: String): Int = {
    input
      .split("\n\n")
      .map(processGroup)
      .sum
  }

  private def processGroup(str: String): Int = {
    str
      .split("\n")
      .flatMap(_.split(""))
      .distinct
      .length
  }

  // ---

  override def part2(input: String): Int = {
    input
      .split("\n\n")
      .map(processGroup2)
      .sum
  }

  private def processGroup2(str: String): Int = {
    val surveys = str.split("\n")

    val num = surveys.length

    surveys
      .flatMap(_.split(""))
      .groupBy(identity)
      .map(_._2.length)
      .count(_ == num)
  }
}
