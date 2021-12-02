package eu.throup.aoc.year2021.day07

import eu.throup.aoc.DayXXSpec

class Day07Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day07

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 37),
    "puzzle input" -> (puzzleInput, 323647)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 168),
    "puzzle input" -> (puzzleInput, 87640209)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
