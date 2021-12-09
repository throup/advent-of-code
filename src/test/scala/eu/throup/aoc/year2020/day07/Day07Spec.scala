package eu.throup.aoc.year2020.day07

import eu.throup.aoc.DayXXSpec

class Day07Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day07

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 4),
    "puzzle input" -> (puzzleInput, 296)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 32),
    "sample 2 input" -> (sample2Input, 126),
    "puzzle input" -> (puzzleInput, 9339)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
