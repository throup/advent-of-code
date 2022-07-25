package eu.throup.aoc.year2015.day05

import eu.throup.aoc.DayXXSpec

class Day05Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day05

  override def testInput1 = Map(
    "ugknbfddgicrmopn" -> ("ugknbfddgicrmopn", 1),
    "aaa" -> ("aaa", 1),
    "jchzalrnumimnmhp" -> ("jchzalrnumimnmhp", 0),
    "haegwjzuvuyypxyu" -> ("haegwjzuvuyypxyu", 0),
    "dvszwmarrgswjxmb" -> ("dvszwmarrgswjxmb", 0),
    "puzzle input" -> (puzzleInput, 255)
  )

  override def testInput2 = Map(
    "qjhvhtzxzqqjkmpb" -> ("qjhvhtzxzqqjkmpb", 1),
    "xxyxx" -> ("xxyxx", 1),
    "uurcxstgmygtbstg" -> ("uurcxstgmygtbstg", 0),
    "ieodomkazucvgmuy" -> ("ieodomkazucvgmuy", 0),
    "qpnxkuldeiituggg" -> ("qpnxkuldeiituggg", 0),
    "puzzle input" -> (puzzleInput, 55)
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
