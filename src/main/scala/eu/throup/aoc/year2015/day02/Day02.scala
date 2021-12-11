package eu.throup.aoc.year2015.day02

import eu.throup.aoc.DayXX

import scala.annotation.tailrec

object Day02 extends DayXX {
  override def part1(input: String) =
    parseInput(input)
      .map(calcPaper(_))
      .sum

  override def part2(input: String) =
    parseInput(input)
      .map(calcRibbon(_))
      .sum

  private def calcPaper(lengths: Array[Int]) = {
    val sides = Seq(
      lengths(0) * lengths(1),
      lengths(0) * lengths(2),
      lengths(1) * lengths(2)
    )

    2 * sides.sum + sides.min
  }

  private def calcRibbon(lengths: Array[Int]) =
    lengths.sorted.take(2).sum * 2 + lengths.product

  def parseInput(input: String): Array[Array[Int]] =
    input.trim
      .split("\\n")
      .map(_.split("x").map(_.toInt))
}
