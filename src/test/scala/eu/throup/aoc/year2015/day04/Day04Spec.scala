package eu.throup.aoc.year2015.day04

import eu.throup.aoc.DayXXSpec
import eu.throup.aoc.year2015.day04.Day04

class Day04Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day04

  override def testInput1 = Map(
    "abcdef" -> ("abcdef", 609043),
    "pqrstuv" -> ("pqrstuv", 1048970),
    "puzzle input" -> (puzzleInput, 346386)
  )

  override def testInput2 = Map(
    "puzzle input" -> (puzzleInput, 9958218)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
