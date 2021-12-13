package eu.throup.aoc.year2021.day13

import eu.throup.aoc.DayXXSpec

class Day13Spec extends DayXXSpec {
  override type returnType = Long | String
  override val testObject = Day13

  override def testInput1 = Map(
    "sample input" -> (sampleInput, 17),
    "puzzle input" -> (puzzleInput, 671)
  )

  override def testInput2 = Map(
    "sample input" -> (
      sampleInput,
      """XXXXX
        |X___X
        |X___X
        |X___X
        |XXXXX""".stripMargin
    ),
    "puzzle input" -> (
      puzzleInput,
      """XXX___XX__XXX__X__X__XX__XXX__X__X_X___
        |X__X_X__X_X__X_X__X_X__X_X__X_X_X__X___
        |X__X_X____X__X_XXXX_X__X_X__X_XX___X___
        |XXX__X____XXX__X__X_XXXX_XXX__X_X__X___
        |X____X__X_X____X__X_X__X_X_X__X_X__X___
        |X_____XX__X____X__X_X__X_X__X_X__X_XXXX""".stripMargin
    ) // PCPHARKL
  )

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
