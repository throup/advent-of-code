package eu.throup.aoc.year2021.day01

import eu.throup.aoc.DayXX

object Day01 extends DayXX {
  override def part1(input: String) =
    countIncreasingTerms(
      parseInput(input)
    )

  override def part2(input: String) =
    countIncreasingTerms(
      movingSum(
        parseInput(input)
      )
    )

  def parseInput(input: String): Seq[Long] =
    input.trim
      .split("\n")
      .map(_.toLong)

  def countIncreasingTerms(working: Seq[Long]) =
    (working.dropRight(1) zip working.drop(1))
      .filter(_ < _)
      .size

  def movingSum(parts: Seq[Long]) =
    parts
      .sliding(3)
      .map(_.sum)
      .toSeq
}
