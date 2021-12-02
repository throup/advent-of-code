package eu.throup.aoc.year2020.day02

import eu.throup.aoc.DayXXSpec
import org.scalatest.freespec.AnyFreeSpec

class Day02Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day02

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 2),
    "puzzle input" -> (puzzleInput, 640)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 1),
    "puzzle input" -> (puzzleInput, 472)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
