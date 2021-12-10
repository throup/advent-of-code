package eu.throup.aoc.year2020.day22

import eu.throup.aoc.DayXXSpec

class Day22Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day22

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 306),
    "puzzle input" -> (puzzleInput, 36257)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 291),
    "puzzle input" -> (puzzleInput, 33304)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
