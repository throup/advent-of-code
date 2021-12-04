package eu.throup.aoc.year2021.day04

import eu.throup.aoc.year2021.DayXX

import scala.annotation.tailrec

object Day04 extends DayXX {
  override def part1(input: String) = {
    val (called, boards) = parseInput(input)
    boards
      .sortBy(_.howSoon(called))
      .head
      .score(called)
  }

  override def part2(input: String) = {
    val (called, boards) = parseInput(input)
    boards
      .sortBy(_.howSoon(called))
      .reverse
      .head
      .score(called)
  }

  def parseInput(input: String) = {
    val lines =
      input.trim
        .split("\n")

    val called =
      lines.head
        .split(",")
        .map(_.toInt)

    (
      called,
      extractBoards(lines.tail)
    )
  }

  @tailrec
  def extractBoards(
      source: Array[String],
      boards: Seq[Board] = Seq()
  ): Seq[Board] =
    if (source.isEmpty) boards
    else extractBoards(source.drop(6), boards :+ Board(source.tail.take(5)))
}
