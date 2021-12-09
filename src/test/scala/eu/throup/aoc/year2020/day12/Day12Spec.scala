package eu.throup.aoc.year2020.day12

import eu.throup.aoc.DayXXSpec

class Day12Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day12

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 25),
    "puzzle input" -> (puzzleInput, 1838)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 286),
    "puzzle input" -> (puzzleInput, 89936)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
