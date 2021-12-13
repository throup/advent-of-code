package eu.throup.aoc.year2021.day11

import eu.throup.aoc.DayXX
import eu.throup.measures.Point

import scala.annotation.tailrec

object Day11 extends DayXX {
  override def part1(input: String) =
    runSteps(
      (FlashingGrid(input), Flasher()),
      100
    ).count

  override def part2(input: String) =
    countStepsUntilZero((FlashingGrid(input), Flasher()))

  @tailrec
  def runSteps(gf: GridWithFlasher, steps: Int): Flasher =
    if (steps == 0) gf._2
    else runSteps(runStep(gf), steps - 1)

  @tailrec
  def countStepsUntilZero(gf: GridWithFlasher, steps: Int = 0): Int =
    if (gf._1.count(_ == 0) == 100) steps
    else countStepsUntilZero(runStep(gf), steps + 1)

  def runStep(gf: GridWithFlasher): GridWithFlasher =
    gf._2.collectFlashes(
      loopFlash(
        gf._1.map(_ + 1)
      )
    )

  @tailrec
  def loopFlash(
      grid: FlashingGrid,
      knownTens: Set[Point] = Set()
  ): FlashingGrid = {
    val tens = knownTens ++ grid.filter(_ == 10).points
    if (tens.isEmpty) grid
    else
      loopFlash(
        grid.flash(tens.head),
        tens.tail
      )
  }
}
