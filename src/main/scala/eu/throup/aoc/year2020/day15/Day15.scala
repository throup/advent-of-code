package eu.throup.aoc.year2020.day15

import eu.throup.aoc.DayXX

import scala.annotation.tailrec

object Day15 extends DayXX {
  override def part1(input: String): Int =
    playTheGame(2020, parseInput(input))

  override def part2(input: String): Int =
    playTheGame(30000000, parseInput(input))

  def playTheGame(target: Int, start: Array[Int]): Int =
    takeATurn(
      start.indices.map(i => start(i) -> ((i + 1), (i + 1))).toMap,
      start(start.length - 1),
      start.length + 1,
      target
    )

  @tailrec
  def takeATurn(
      tracker: Map[Int, (Int, Int)],
      last: Int,
      turn: Int,
      target: Int
  ): Int =
    if (target - turn < 0) last
    else {
      val newLast = tracker(last)._1 - tracker(last)._2

      val newEntry: (Int, Int) =
        tracker
          .get(newLast)
          .map { case (o, _) => (turn, o) }
          .getOrElse((turn, turn))

      takeATurn(
        tracker + (newLast -> newEntry),
        newLast,
        turn + 1,
        target
      )
    }

  def parseInput(input: String): Array[Int] =
    input
      .split(",")
      .map(_.toInt)
}
