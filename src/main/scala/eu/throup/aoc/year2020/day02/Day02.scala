package eu.throup.aoc.year2020.day02

import eu.throup.aoc.DayXX

object Day02 extends DayXX {
  override def part1(input: String): Int =
    parseInput(input)
      .map(PasswordPolicy1.instance()(_))
      .count(_.test())

  override def part2(input: String): Int = {
    parseInput(input)
      .map(PasswordPolicy2.instance()(_))
      .count(_.test())
  }

  def parseInput(input: String): Seq[String] =
    input.trim
      .split("\n")
}
