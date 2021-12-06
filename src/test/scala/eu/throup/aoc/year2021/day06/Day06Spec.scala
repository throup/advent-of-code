package eu.throup.aoc.year2021.day06

import eu.throup.aoc.year2021.DayXXSpec

class Day06Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day06

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 5934),
    "puzzle input" -> (puzzleInput, 394994)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 26984457539L),
    "puzzle input" -> (puzzleInput, 1765974267455L)
  )

  def sampleInput = loadResource("sampleinput.txt")

  def puzzleInput = loadResource("puzzleinput.txt")
}
