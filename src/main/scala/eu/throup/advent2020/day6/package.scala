package eu.throup.advent2020

import scala.util.matching.Regex

package object day6 {
  def part1(input: String): Int = {
    input.split("\n\n")
      .map(processGroup)
      .sum
  }

  private def processGroup(str: String): Int = {
    str.split("\n")
      .flatMap(_.split(""))
      .distinct.length
  }

  // ---

  def part2(input: String): Int = {
    input.split("\n\n")
      .map(processGroup2)
      .sum
  }

  private def processGroup2(str: String): Int = {
    val surveys = str.split("\n")

    val num = surveys.length

    surveys.flatMap(_.split(""))
      .groupBy(identity)
      .map(a => a._2.length)
      .count(_ == num)
  }
}
