package eu.throup.aoc.year2021.day12

import eu.throup.aoc.DayXXSpec

class Day12Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day12

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 10),
    "sample 2 input" -> (sample2Input, 19),
    "sample 3 input" -> (sample3Input, 226),
    "puzzle input" -> (puzzleInput, 4885)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 36),
    "sample 2 input" -> (sample2Input, 103),
    "sample 3 input" -> (sample3Input, 3509),
    "puzzle input" -> (puzzleInput, 117095)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def sample3Input = loadResource("sample3input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
