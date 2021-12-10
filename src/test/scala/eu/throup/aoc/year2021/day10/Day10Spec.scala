package eu.throup.aoc.year2021.day10

import eu.throup.aoc.year2021.DayXXSpec

class Day10Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day10

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 26397),
    "puzzle input" -> (puzzleInput, 387363)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 288957),
    "puzzle input" -> (puzzleInput, 4330777059L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
