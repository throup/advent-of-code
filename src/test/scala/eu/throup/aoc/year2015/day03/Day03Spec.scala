package eu.throup.aoc.year2015.day03

import eu.throup.aoc.DayXXSpec
import eu.throup.aoc.year2015.day03.Day03

class Day03Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day03

  override def testInput1 = Map(
    ">" -> (">", 2),
    "^>v<" -> ("^>v<", 4),
    "^v^v^v^v^v" -> ("^v^v^v^v^v", 2),
    "puzzle input" -> (puzzleInput, 2572)
  )

  override def testInput2 = Map(
    "^v" -> ("^v", 3),
    "^>v<" -> ("^>v<", 3),
    "^v^v^v^v^v" -> ("^v^v^v^v^v", 11),
    "puzzle input" -> (puzzleInput, 2631)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
