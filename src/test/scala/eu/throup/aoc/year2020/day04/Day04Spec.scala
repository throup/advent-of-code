package eu.throup.aoc.year2020.day04

import eu.throup.aoc.DayXXSpec

class Day04Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day04

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 2),
    "sample 2 input" -> (sample2Input, 8),
    "puzzle input" -> (puzzleInput, 230)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 2),
    "sample 2 input" -> (sample2Input, 4),
    "puzzle input" -> (puzzleInput, 156)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
