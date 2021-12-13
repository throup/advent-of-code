package eu.throup.aoc.year2021.day11

import eu.throup.aoc.DayXX
import eu.throup.measures.Point

import scala.annotation.tailrec

object Day11 extends DayXX {
  override def part1(input: String) = {
    val parsed = Grid(input)
    runSteps(parsed, 100).flashes
  }

  override def part2(input: String) = {
    val parsed = Grid(input)
    countStepsUntilZero(parsed)
  }

  @tailrec
  def runSteps(grid: Grid, steps: Int): Grid =
    if (steps == 0) grid
    else runSteps(runStep(grid), steps - 1)

  @tailrec
  def countStepsUntilZero(grid: Grid, steps: Int = 0): Int =
    if (grid.count(_ == 0) == 100) steps
    else countStepsUntilZero(runStep(grid), steps + 1)

  def runStep(grid: Grid): Grid =
    loopFlash(
      grid.map(_ + 1)
    ).collectFlashes

  @tailrec
  def loopFlash(grid: Grid, knownTens: Set[Point] = Set()): Grid = {
    val tens = knownTens ++ grid.filter(_ == 10).points
    if (tens.isEmpty) grid
    else
      loopFlash(
        grid.flash(tens.head),
        tens.tail
      )
  }
}
