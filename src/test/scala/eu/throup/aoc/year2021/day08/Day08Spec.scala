package eu.throup.aoc.year2021.day08

import eu.throup.aoc.DayXXSpec

class Day08Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day08

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 26),
    "puzzle input" -> (puzzleInput, 470)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 61229),
    "puzzle input" -> (puzzleInput, 989396)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
