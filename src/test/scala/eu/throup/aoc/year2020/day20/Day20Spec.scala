package eu.throup.aoc.year2020.day20

import eu.throup.aoc.DayXXSpec

class Day20Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day20

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 20899048083289L),
    "puzzle input" -> (puzzleInput, 17032646100079L)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 273),
    "puzzle input" -> (puzzleInput, 2006)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
