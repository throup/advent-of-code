package eu.throup.aoc.year2020.day05

import eu.throup.aoc.DayXXSpec
import org.scalatest.freespec.AnyFreeSpec

class Day05Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day05

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 820),
    "sample 2 input" -> (sample2Input, 31),
    "puzzle input" -> (puzzleInput, 908)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 120),
    "sample 2 input" -> (sample2Input, 28),
    "puzzle input" -> (puzzleInput, 619)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
