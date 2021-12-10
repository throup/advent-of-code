package eu.throup.aoc.year2015.day01

import eu.throup.aoc.DayXXSpec

class Day01Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day01

  override def testInput1 = Map(
    "(())" -> ("(())", 0),
    "()()" -> ("()()", 0),
    "(((" -> ("(((", 3),
    "(()(()(" -> ("(()(()(", 3),
    "))(((((" -> ("))(((((", 3),
    "())" -> ("())", -1),
    "))(" -> ("))(", -1),
    ")))" -> (")))", -3),
    ")())())" -> (")())())", -3),
    "puzzle input" -> (puzzleInput, 280)
  )

  override def testInput2 = Map(
    ")" -> (")", 1),
    "()())" -> ("()())", 5),
    "puzzle input" -> (puzzleInput, 1797)
  )

  def puzzleInput = loadResource("puzzleinput.txt")
}
