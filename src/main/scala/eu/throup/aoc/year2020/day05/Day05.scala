package eu.throup.aoc.year2020.day05

import eu.throup.aoc.DayXX

import scala.util.matching.Regex

object Day05 extends DayXX {
  override def part1(input: String): Int = {
    val passes: Array[Int] = mapInputToIds(input)
    passes.max
  }

  override def part2(input: String): Int = {
    val passes: _root_.scala.Array[Int] = mapInputToIds(input)

    (passes.min to passes.max)
      .find(!passes.contains(_))
      .get
  }

  def mapInputToIds(input: String): Array[Int] =
    input
      .split("\n")
      .map(BoardingPass(_))
      .map(_.id)
}
