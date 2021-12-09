package eu.throup.aoc.year2021.day09

import eu.throup.aoc.year2021.DayXXSpec

class Day09Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day09

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 15),
    "puzzle input" -> (puzzleInput, 526)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 1134),
    "puzzle input" -> (puzzleInput, 1123524)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
