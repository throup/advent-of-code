package eu.throup.aoc.year2021.day15

import eu.throup.aoc.DayXXSpec

import org.scalatest.time.SpanSugar._

class Day15Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day15

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 40),
    "sample 2 input" -> (sample2Input, 10),
    "puzzle input" -> (puzzleInput, 373)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 315),
    "sample 2 input" -> (sample2Input, 202),
    "puzzle input" -> (puzzleInput, 2868)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def sample2Input = loadResource("sample2input.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
