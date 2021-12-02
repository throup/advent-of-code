package eu.throup.aoc.year2021.day03

import eu.throup.aoc.DayXXSpec

class Day03Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day03

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 198),
    "puzzle input" -> (puzzleInput, 693486)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 230),
    "puzzle input" -> (puzzleInput, 3379326)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
