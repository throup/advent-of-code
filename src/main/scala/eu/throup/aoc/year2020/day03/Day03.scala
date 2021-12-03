package eu.throup.aoc.year2020.day03

import eu.throup.aoc.DayXX

object Day03 extends DayXX {
  override def part1(input: String): Long = {
    val slope = new SkiSlope(input)
    slope.trees(3, 1)
  }

  override def part2(input: String): Long = {
    val slope = new SkiSlope(input)

    val a = slope.trees(1, 1)
    val b = slope.trees(3, 1)
    val c = slope.trees(5, 1)
    val d = slope.trees(7, 1)
    val e = slope.trees(1, 2)

    a * b * c * d * e
  }

  def parseInput(input: String): Seq[String] =
    input.trim
      .split("\n")
}
