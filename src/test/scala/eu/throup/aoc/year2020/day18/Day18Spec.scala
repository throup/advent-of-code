package eu.throup.aoc.year2020.day18

import eu.throup.aoc.DayXXSpec

class Day18Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day18

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 26457),
    "puzzle input" -> (puzzleInput, 12956356593940L)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 694173),
    "puzzle input" -> (puzzleInput, 94240043727614L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
