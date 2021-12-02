package eu.throup.advent2020

package object day18 {
  def part1(input: String): Long = {
    val expressions = input.split("\n")
    val calculators = expressions.map(Calculator)
    val results = calculators.map(_.result)
    val sum = results.sum
    sum
  }

  // ---

  def part2(input: String): Long = {
    val expressions = input.split("\n")
    val calculators = expressions.map(Calculator2)
    val results = calculators.map(_.result)
    val sum = results.sum
    sum
  }

  // ---
}
