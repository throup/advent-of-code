package eu.throup.aoc.year2015.day07

import eu.throup.aoc.DayXXSpec

class Day07Spec extends DayXXSpec {
  override type returnType = Int
  override val testObject = Day07

  override def testInput1 = Map(
    "sample d" -> (sampleInput + "\n" + "d -> a", 72),
    "sample e" -> (sampleInput + "\n" + "e -> a", 507),
    "sample f" -> (sampleInput + "\n" + "f -> a", 492),
    "sample g" -> (sampleInput + "\n" + "g -> a", 114),
    "sample h" -> (sampleInput + "\n" + "h -> a", 65412),
    "sample i" -> (sampleInput + "\n" + "i -> a", 65079),
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
