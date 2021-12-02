package eu.throup.aoc.year2021.day06

import eu.throup.aoc.DayXX

import scala.annotation.tailrec

object Day06 extends DayXX {
  override def part1(input: String) = {
    val day0 = parseInput(input)
    fishAfterTime(day0, 80)
  }

  override def part2(input: String) = {
    val day0 = parseInput(input)
    fishAfterTime(day0, 256)
  }

  def fishAfterTime(fish: Seq[Int], days: Int): Long =
    loopIt(seqToMap(fish), days).map { case (_, f) => f }.sum

  def seqToMap(seq: Seq[Int]): Map[Int, Long] =
    seq
      .groupBy(identity)
      .map { case (k, v) => k -> v.size.toLong }

  @tailrec
  def loopIt(prev: Map[Int, Long], left: Int = 0): Map[Int, Long] =
    if (left == 0) prev
    else loopIt(nextDay(prev), left - 1)

  // I could have used a .map() function but, as there is a low upper limit,
  // I think this looks clearer
  def nextDay(fish: Map[Int, Long]): Map[Int, Long] =
    Map(
      0 -> fish.getOrElse(1, 0L),
      1 -> fish.getOrElse(2, 0L),
      2 -> fish.getOrElse(3, 0L),
      3 -> fish.getOrElse(4, 0L),
      4 -> fish.getOrElse(5, 0L),
      5 -> fish.getOrElse(6, 0L),
      6 -> (fish.getOrElse(7, 0L) + fish.getOrElse(0, 0L)),
      7 -> fish.getOrElse(8, 0L),
      8 -> fish.getOrElse(0, 0L)
    )

  def parseInput(input: String): Array[Int] =
    input.trim
      .split(",")
      .map(_.toInt)
}
