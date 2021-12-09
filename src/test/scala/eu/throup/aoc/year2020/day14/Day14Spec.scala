package eu.throup.aoc.year2020.day14

import eu.throup.aoc.DayXXSpec

class Day14Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day14

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 165),
    "puzzle input" -> (puzzleInput, 11884151942312L)
  )

  override def testInput2 = Map(
    "sample 2 input" -> (sample2Input, 208),
    "puzzle input" -> (puzzleInput, 2625449018811L)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
