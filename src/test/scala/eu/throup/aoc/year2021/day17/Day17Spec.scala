package eu.throup.aoc.year2021.day17

import eu.throup.aoc.DayXXSpec
import eu.throup.measures.Point

class Day17Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day17

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 45),
    "puzzle input" -> (puzzleInput, 30628)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 112),
    "puzzle input" -> (puzzleInput, 4433)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
