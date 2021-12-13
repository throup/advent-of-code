package eu.throup.aoc.year2021.day13

import eu.throup.aoc.DayXX

object Day13 extends DayXX {
  override def part1(input: String) = {
    val (paper: Paper, folds: Seq[Fold]) = parseInput(input)
    paper(folds.head).size
  }

  override def part2(input: String) = {
    val (paper: Paper, folds: Seq[Fold]) = parseInput(input)
    paper(folds).toString
  }

  def parseInput(input: String) = {
    val parts: Array[Array[String]] = input.trim
      .split("\n\n")
      .map(_.split("\n"))

    (
      Paper(parts(0).map(Point(_))),
      parts(1).map(Fold(_)).toSeq
    )
  }
}
