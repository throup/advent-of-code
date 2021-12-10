package eu.throup.aoc.year2020.day24

import eu.throup.aoc.DayXXSpec

class Day24Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day24

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 10),
    "puzzle input" -> (puzzleInput, 289)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 2208),
    "puzzle input" -> (puzzleInput, 3551)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
