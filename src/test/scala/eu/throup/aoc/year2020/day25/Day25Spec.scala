package eu.throup.aoc.year2020.day25

import eu.throup.aoc.DayXXSpec
import eu.throup.aoc.year2020.day25.Day25.*
import org.scalatest.freespec.AnyFreeSpec

class Day25Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day25

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 14897079),
    "puzzle input" -> (puzzleInput, 7032853)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 0),
    "puzzle input" -> (puzzleInput, 0)
  )

  def sampleInput = "17807724\n5764801"
  def puzzleInput = "7573546\n17786549"
}
