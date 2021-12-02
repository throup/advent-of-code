package eu.throup.advent2020

import scala.util.matching.Regex

package object day5 {
  def part1(input: String): Int = {
    val passes = input.split("\n")
      .map(new BoardingPass(_))
      .map(_.id)

    passes.max
  }

  // ---

  def part2(input: String): Int = {
    val passes = input.split("\n")
      .map(new BoardingPass(_))
      .map(_.id)

    (passes.min to passes.max)
      .find(n => !passes.contains(n))
      .get
  }
}
