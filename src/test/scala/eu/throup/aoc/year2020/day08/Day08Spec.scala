package eu.throup.aoc.year2020.day08

import eu.throup.aoc.DayXXSpec

class Day08Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day08

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 5),
    "puzzle input" -> (puzzleInput, 1446)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 8),
    "puzzle input" -> (puzzleInput, 1403)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
