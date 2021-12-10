package eu.throup.aoc.year2015.day01

import eu.throup.aoc.DayXX

import scala.annotation.tailrec

object Day01 extends DayXX {
  override def part1(input: String) = {
    val sizes = parseInput(input)
      .groupBy(identity)
      .mapValues(_.size)
    sizes.getOrElse('(', 0) - sizes.getOrElse(')', 0)
  }

  override def part2(input: String) =
    follow(parseInput(input))

  @tailrec
  def follow(remaining: Seq[Char], pos: Int = 0, floor: Int = 0): Int =
    if (floor < 0) pos
    else {
      val delta = remaining.head match {
        case '(' => 1
        case ')' => -1
        case _   => 0
      }
      follow(remaining.tail, pos + 1, floor + delta)
    }

  def parseInput(input: String): Array[Char] =
    input.trim.toCharArray
}
