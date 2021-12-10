package eu.throup.aoc.year2020.day18

import eu.throup.aoc.DayXX

object Day18 extends DayXX {
  override def part1(input: String): Long =
    input
      .split("\n")
      .map(Calculator1(_))
      .map(_.result)
      .sum

  override def part2(input: String): Long =
    input
      .split("\n")
      .map(Calculator2(_))
      .map(_.result)
      .sum
}
