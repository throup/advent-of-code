package eu.throup.aoc.year2021.day10

import eu.throup.aoc.DayXX
import eu.throup.aoc.year2021.day10.CommandChar.{isOpener, partner}
import eu.throup.aoc.year2021.day10.Walker.{
  Complete,
  Corrupt,
  Incomplete,
  WalkOutcome
}

object Day10 extends DayXX {
  override def part1(input: String) = {
    val lines = parseInput(input)
    lines
      .map(corruptionScoreWalker(_))
      .sum
  }

  override def part2(input: String) = {
    val lines = parseInput(input)
    val scores =
      lines
        .map(completeLineWalker(_))
        .collect { case Some(a) => a }
        .map(completionScore(_))
        .sorted
    val mid = scores.length / 2
    scores(mid)
  }

  def completionScore(remaining: String, running: Long = 0): Long =
    if (remaining.isEmpty) running
    else
      completionScore(
        remaining.tail,
        running * 5 + Map(
          ')' -> 1,
          ']' -> 2,
          '}' -> 3,
          '>' -> 4
        )(remaining.head)
      )

  val completeLineWalker: Walker[Option[String]] =
    Walker((o, _, w) =>
      o match {
        case Complete   => Some("")
        case Incomplete => Some(w.map(partner(_)).mkString)
        case Corrupt    => None
      }
    )

  val corruptionScoreWalker: Walker[Long] =
    Walker((o, r, _) =>
      o match {
        case Corrupt =>
          Map(
            ')' -> 3,
            ']' -> 57,
            '}' -> 1197,
            '>' -> 25137
          )(r.head)
        case _ => 0
      }
    )

  def parseInput(input: String) =
    input.trim
      .split("\\n")
}
