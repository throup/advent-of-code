package eu.throup.aoc.year2021.day20

import eu.throup.aoc.DayXXSpec

class Day20Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day20

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 35),
    "puzzle input" -> (puzzleInput, 5846)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 3351),
    "puzzle input" -> (puzzleInput, 21149)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
