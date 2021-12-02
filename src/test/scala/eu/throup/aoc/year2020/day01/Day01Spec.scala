package eu.throup.aoc.year2020.day01

import eu.throup.aoc.DayXXSpec
import org.scalatest.freespec.AnyFreeSpec

class Day01Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day01

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 514579),
    "puzzle input" -> (puzzleInput, 138379)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 241861950),
    "puzzle input" -> (puzzleInput, 85491920)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
