package eu.throup.aoc.year2020.day09

import eu.throup.aoc.DayXXSpec

class Day09Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day09

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 127),
    "puzzle input" -> (puzzleInput, 1038347917)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 62),
    "puzzle input" -> (puzzleInput, 137394018)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
