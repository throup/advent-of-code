package eu.throup.aoc.year2020.day06

import eu.throup.aoc.DayXXSpec

class Day06Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day06

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 11),
    "puzzle input" -> (puzzleInput, 6662)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 6),
    "puzzle input" -> (puzzleInput, 3382)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
