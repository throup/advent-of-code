package eu.throup.aoc.year2021.day19

import eu.throup.aoc.DayXXSpec

class Day19Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day19

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 79),
    "sample 2 input" -> (sample2Input, 39),
    "sample 3 input" -> (sample3Input, 40),
    "puzzle input" -> (puzzleInput, 449)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 3621),
    "puzzle input" -> (puzzleInput, 13128)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def sample3Input = loadResource("sample3input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
