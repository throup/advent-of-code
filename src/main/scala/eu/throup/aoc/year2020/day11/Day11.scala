package eu.throup.aoc.year2020.day11

import eu.throup.aoc.DayXX

object Day11 extends DayXX {
  override def part1(input: String): Long = {
    val plan = new SeatingPlanV1(input)
    plan.shuffleCount()
    plan.occupied()
  }

  override def part2(input: String): Long = {
    val plan = new SeatingPlanV2(input)
    val count = plan.shuffleCount()
    plan.occupied()
  }
}
