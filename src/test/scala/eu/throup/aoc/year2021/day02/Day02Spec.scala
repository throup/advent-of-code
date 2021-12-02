package eu.throup.aoc.year2021.day02

import eu.throup.aoc.year2021.DayXXSpec

import scala.io.Source

class Day02Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day02

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 150),
    "puzzle input" -> (puzzleInput, 1524750)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 900),
    "puzzle input" -> (puzzleInput, 1592426537)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
