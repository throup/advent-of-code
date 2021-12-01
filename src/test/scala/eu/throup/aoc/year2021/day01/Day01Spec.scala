package eu.throup.aoc.year2021.day01

import eu.throup.aoc.year2021.DayXXSpec

import scala.io.Source

class Day01Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day01

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 7),
    "puzzle input" -> (puzzleInput, 1616)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 5),
    "puzzle input" -> (puzzleInput, 1645)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
