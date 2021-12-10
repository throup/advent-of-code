package eu.throup.aoc.year2020.day16

import eu.throup.aoc.DayXXSpec

class Day16Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day16

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 71),
    "puzzle input" -> (puzzleInput, 20058)
  )

  override def testInput2 = Map(
    "sample input" -> (sample2Input, 156),
    "puzzle input" -> (puzzleInput, 366871907221L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
