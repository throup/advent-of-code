package eu.throup.aoc.year2020.day15

import eu.throup.aoc.DayXXSpec

class Day15Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day15

  override def testInput1 = Map(
    "0,3,6" -> ("0,3,6", 436),
    "1,3,2" -> ("1,3,2", 1),
    "2,1,3" -> ("2,1,3", 10),
    "1,2,3" -> ("1,2,3", 27),
    "2,3,1" -> ("2,3,1", 78),
    "3,2,1" -> ("3,2,1", 438),
    "3,1,2" -> ("3,1,2", 1836),
    "puzzle input" -> (puzzleInput, 1696)
  )

  override def testInput2 = Map(
    "0,3,6" -> ("0,3,6", 175594),
    "1,3,2" -> ("1,3,2", 2578),
    "2,1,3" -> ("2,1,3", 3544142),
    "1,2,3" -> ("1,2,3", 261214),
    "2,3,1" -> ("2,3,1", 6895259),
    "3,2,1" -> ("3,2,1", 18),
    "3,1,2" -> ("3,1,2", 362),
    "puzzle input" -> (puzzleInput, 37385)
  )

  def puzzleInput = "12,1,16,3,11,0"
}
