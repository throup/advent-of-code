package eu.throup.aoc.year2021.day21

import eu.throup.aoc.DayXXSpec

class Day21Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day21

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 739785),
    "puzzle input" -> (puzzleInput, 918081)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 444356092776315L),
    "puzzle input" -> (puzzleInput, 158631174219251L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
