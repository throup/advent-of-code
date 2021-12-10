package eu.throup.aoc.year2020.day19

import eu.throup.aoc.DayXX

object Day19 extends DayXX {
  override def part1(input: String): Long =
    new RuleEngine(input).solve

  override def part2(input: String): Long =
    new RuleEngine(input).solve
}
