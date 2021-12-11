package eu.throup.aoc.year2015.day02

import eu.throup.aoc.DayXXSpec

class Day02Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day02

  override def testInput1 = Map(
    "2x3x4" -> ("2x3x4", 58),
    "1x1x10" -> ("1x1x10", 43),
    "puzzle input" -> (puzzleInput, 1606483)
  )

  override def testInput2 = Map(
    "2x3x4" -> ("2x3x4", 34),
    "1x1x10" -> ("1x1x10", 14),
    "puzzle input" -> (puzzleInput, 3842356)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
