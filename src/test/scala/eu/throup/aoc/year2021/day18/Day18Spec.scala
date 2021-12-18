package eu.throup.aoc.year2021.day18

import eu.throup.aoc.DayXXSpec

class Day18Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day18

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 4140),
    "puzzle input" -> (puzzleInput, 3981)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 3993),
    "puzzle input" -> (puzzleInput, 4687)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
