package eu.throup.aoc.year2020.day03

import eu.throup.aoc.DayXXSpec

class Day03Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day03

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 7),
    "puzzle input" -> (puzzleInput, 265)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 336),
    "puzzle input" -> (puzzleInput, 3154761400L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
