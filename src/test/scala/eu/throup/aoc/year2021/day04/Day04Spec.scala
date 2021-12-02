package eu.throup.aoc.year2021.day04

import eu.throup.aoc.DayXXSpec

class Day04Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day04

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 4512),
    "puzzle input" -> (puzzleInput, 12796)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 1924),
    "puzzle input" -> (puzzleInput, 18063)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
