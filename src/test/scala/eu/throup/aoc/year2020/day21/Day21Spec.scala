package eu.throup.aoc.year2020.day21

import eu.throup.aoc.DayXXSpec

class Day21Spec extends DayXXSpec {
  override type returnType = Long | String
  override val testObject = Day21

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 5),
    "puzzle input" -> (puzzleInput, 2078)
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, "mxmxvkd,sqjhc,fvjkl"),
    "puzzle input" -> (puzzleInput, "lmcqt,kcddk,npxrdnd,cfb,ldkt,fqpt,jtfmtpd,tsch")
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
