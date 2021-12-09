package eu.throup.aoc.year2020.day13

import eu.throup.aoc.DayXXSpec

class Day13Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day13

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 295),
    "puzzle input" -> (puzzleInput, 3997)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 1068781),
    "sample 2 input" -> (sample2Input, 3417),
    "sample 3 input" -> (sample3Input, 754018),
    "sample 4 input" -> (sample4Input, 779210),
    "sample 5 input" -> (sample5Input, 1261476),
    "sample 6 input" -> (sample6Input, 1202161486),
    "puzzle input" -> (puzzleInput, 500033211739354L)
  )

  def sampleInput = "939\n7,13,x,x,59,x,31,19"
  def sample2Input = "939\n17,x,13,19"
  def sample3Input = "939\n67,7,59,61"
  def sample4Input = "939\n67,x,7,59,61"
  def sample5Input = "939\n67,7,x,59,61"
  def sample6Input = "939\n1789,37,47,1889"
  def puzzleInput = loadResource("puzzleinput.txt")
}
