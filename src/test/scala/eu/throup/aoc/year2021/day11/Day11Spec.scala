package eu.throup.aoc.year2021.day11

import eu.throup.aoc.DayXXSpec

class Day11Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day11

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 1656),
    "puzzle input" -> (puzzleInput, 1747)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 195),
    "puzzle input" -> (puzzleInput, 505)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
