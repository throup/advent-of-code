package eu.throup.aoc.year2020.day19

import eu.throup.aoc.DayXXSpec

class Day19Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day19

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 2),
    "puzzle input" -> (puzzleInput, 124)
  )

  override def testInput2 = Map(
    "sample input" -> (sample2Input, 12),
    "puzzle input" -> (puzzle2Input, 228)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzle2Input = loadResource("puzzle2input.txt")
}
