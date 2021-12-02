package eu.throup.advent2020

import scala.util.control.Breaks.{break, breakable}

package object day11 {
  def part1(input: String): Long = {
    val plan = new SeatingPlanV1(input)
    plan.shuffleCount()
    plan.occupied()
  }

  // ---

  def part2(input: String): Long = {
    val plan = new SeatingPlanV2(input)
    val count = plan.shuffleCount()
    plan.occupied()
  }

  // ---
}
