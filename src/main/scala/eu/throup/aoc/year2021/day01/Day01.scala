package eu.throup.aoc.year2021.day01

import eu.throup.aoc.year2021.DayXX

import scala.runtime.Tuple2Zipped

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
    (working.dropRight(1) zip working.drop(1)).filter { case (a, b) =>
      a < b
    }.size

  def movingSum(parts: Seq[Long]) =
    (
      parts.dropRight(2),
      parts.drop(1).dropRight(1),
      parts.drop(2)
    ).zipped
      .map { case (a, b, c) =>
        a + b + c
      }
}
