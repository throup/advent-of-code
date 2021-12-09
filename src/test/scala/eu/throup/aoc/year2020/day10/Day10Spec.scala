package eu.throup.aoc.year2020.day10

import eu.throup.aoc.DayXXSpec

class Day10Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day10

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 35),
    "sample 2 input" -> (sample2Input, 220),
    "puzzle input" -> (puzzleInput, 1885)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 8),
    "sample 2 input" -> (sample2Input, 19208),
    "puzzle input" -> (puzzleInput, 2024782584832L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
