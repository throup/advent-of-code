package eu.throup.aoc.year2021.day18

import eu.throup.aoc.DayXX

import scala.annotation.tailrec

object Day18 extends DayXX {
  override def part1(input: String) =
    SnailfishElement
      .addAll(
        parseInput(input)
      )
      .magnitude

  override def part2(input: String) =
    parseInput(input)
      .combinations(2)
      .flatMap(s => Seq(s(0) + s(1), s(1) + s(0)))
      .map(_.magnitude)
      .max

  def parseInput(input: String): Seq[SnailfishElement] =
    input.trim
      .split("\\n")
      .map(SnailfishElement(_))
}
