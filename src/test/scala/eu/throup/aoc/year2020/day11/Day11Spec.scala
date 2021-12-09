package eu.throup.aoc.year2020.day11

import eu.throup.aoc.DayXXSpec

class Day11Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day11

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 37),
    "puzzle input" -> (puzzleInput, 2183)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 26),
    "puzzle input" -> (puzzleInput, 1990)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
