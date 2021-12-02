package eu.throup.aoc.year2020.day01

import eu.throup.aoc.DayXX

import scala.util.control.Breaks.{break, breakable}

object Day01 extends DayXX {
  override def part1(input: String) =
    solution(parseInput(input), 2, 2020)

  override def part2(input: String) =
    solution(parseInput(input), 3, 2020)

  private def solution(input: Seq[Int], n: Int, target: Int) = {
    input
      .combinations(n)
      .filter(_.sum == target)
      .toSeq
      .head
      .reduce(_ * _)
  }

  def parseInput(input: String): Seq[Int] =
    input.trim
      .split("\n")
      .map(_.toInt)
}
