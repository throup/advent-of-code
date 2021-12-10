package eu.throup.aoc.year2020.day17

import eu.throup.aoc.DayXXSpec

class Day17Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day17

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 112),
    "puzzle input" -> (puzzleInput, 336)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 848),
    "puzzle input" -> (puzzleInput, 2620)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
