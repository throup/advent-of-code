package eu.throup.aoc.year2021.day14

import eu.throup.aoc.DayXXSpec

class Day14Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day14

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 1588),
    "puzzle input" -> (puzzleInput, 3143)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 2188189693529L),
    "puzzle input" -> (puzzleInput, 4110215602456L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
