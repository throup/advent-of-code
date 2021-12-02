package eu.throup.aoc.year2021.day05

import eu.throup.aoc.DayXXSpec

class Day05Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day05

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 5),
    "puzzle input" -> (puzzleInput, 4826)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 12),
    "puzzle input" -> (puzzleInput, 16793)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
