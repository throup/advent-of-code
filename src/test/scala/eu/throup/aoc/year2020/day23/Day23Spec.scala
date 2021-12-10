package eu.throup.aoc.year2020.day23

import eu.throup.aoc.DayXXSpec

class Day23Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day23

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 67384529),
    "puzzle input" -> (puzzleInput, 75893264)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 149245887792L),
    "puzzle input" -> (puzzleInput, 38162588308L)
  )

  def sampleInput = "389125467"
  def puzzleInput = "974618352"
}
