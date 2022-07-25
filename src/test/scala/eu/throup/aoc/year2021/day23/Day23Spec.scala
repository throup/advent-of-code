package eu.throup.aoc.year2021.day23

import eu.throup.aoc.DayXXSpec

class Day23Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day23

  override def testInput1 = Map(
    "single line" -> (singleLine, 8470),
    "sample input" -> (sampleInput, 12521),
    "puzzle input" -> (puzzleInput, 19167) // < 19183
  )

  override def testInput2 = Map(
    "sample input" -> (sampleInput, 44169),
    "puzzle input" -> (puzzleInput, 47611) // > 47511 && < 47711 && !47691
  )

  "parseInput" - {
    "for single line" in {
      State(singleLine) shouldBe State(
        Array(None, None, None, None, None, None, None),
        Array(Some('D')),
        Array(Some('C')),
        Array(Some('B')),
        Array(Some('A'))
      )
    }

    "for some in the hallway" in {
      State(someInTheHallway) shouldBe State(
        Array(None, Some('A'), None, None, Some('C'), None, None),
        Array(Some('D')),
        Array(None),
        Array(Some('B')),
        Array(None)
      )
    }

    "for two lines" in {
      State(puzzleInput) shouldBe State(
        Array(None, None, None, None, None, None, None),
        Array(Some('D'), Some('D')),
        Array(Some('A'), Some('C')),
        Array(Some('C'), Some('B')),
        Array(Some('A'), Some('B'))
      )
    }

    "for four lines" in {
      State(expandInputForPart2(puzzleInput)) shouldBe State(
        Array(None, None, None, None, None, None, None),
        Array(Some('D'), Some('D'), Some('D'), Some('D')),
        Array(Some('A'), Some('C'), Some('B'), Some('C')),
        Array(Some('C'), Some('B'), Some('A'), Some('B')),
        Array(Some('A'), Some('A'), Some('C'), Some('B'))
      )
    }
  }

  "nextStates" - {
    "for single line" - {
      val state = State(singleLine)
      val nextStates: Set[State] = state.nextStates

      "should include new states for D" in {
        Seq(
          State("""#############
                  |#D..........#
                  |###.#C#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.D.........#
                  |###.#C#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#...D.......#
                  |###.#C#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.....D.....#
                  |###.#C#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.......D...#
                  |###.#C#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.........D.#
                  |###.#C#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#..........D#
                  |###.#C#B#A###
                  |  #########""".stripMargin)
        ).forall(nextStates.contains(_)) shouldBe true
      }

      "should include new states for C" in {
        Seq(
          State("""#############
                  |#C..........#
                  |###D#.#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.C.........#
                  |###D#.#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#...C.......#
                  |###D#.#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.....C.....#
                  |###D#.#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.......C...#
                  |###D#.#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.........C.#
                  |###D#.#B#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#..........C#
                  |###D#.#B#A###
                  |  #########""".stripMargin)
        ).forall(nextStates.contains(_)) shouldBe true
      }

      "should include new states for B" in {
        Seq(
          State("""#############
                  |#B..........#
                  |###D#C#.#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.B.........#
                  |###D#C#.#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#...B.......#
                  |###D#C#.#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.....B.....#
                  |###D#C#.#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.......B...#
                  |###D#C#.#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#.........B.#
                  |###D#C#.#A###
                  |  #########""".stripMargin),
          State("""#############
                  |#..........B#
                  |###D#C#.#A###
                  |  #########""".stripMargin)
        ).forall(nextStates.contains(_)) shouldBe true
      }

      "should include new states for A" in {
        Seq(
          State("""#############
                  |#A..........#
                  |###D#C#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#.A.........#
                  |###D#C#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#...A.......#
                  |###D#C#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#.....A.....#
                  |###D#C#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#.......A...#
                  |###D#C#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#.........A.#
                  |###D#C#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#..........A#
                  |###D#C#B#.###
                  |  #########""".stripMargin)
        ).forall(nextStates.contains(_)) shouldBe true
      }
    }
    "for some in the hallway" - {
      val state = State(someInTheHallway)
      val nextStates: Set[State] = state.nextStates

      "should include new states for D" in {
        Seq(
          State("""#############
                  |#.A.D...C...#
                  |###.#.#B#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#.A...D.C...#
                  |###.#.#B#.###
                  |  #########""".stripMargin)
        ).forall(nextStates.contains(_)) shouldBe true
      }

      "should include new states for B" in {
        Seq(
          State("""#############
                  |#.A...B.C...#
                  |###D#.#.#.###
                  |  #########""".stripMargin),
          State("""#############
                  |#.A.B...C...#
                  |###D#.#.#.###
                  |  #########""".stripMargin)
        ).forall(nextStates.contains(_)) shouldBe true
      }
    }
  }

  def expandInputForPart2(input: String) = {
    val lines = input.split("\n")
    val insert = Seq(
      "###D#C#B#A###",
      "###D#B#A#C###"
    )
    (lines.take(3) ++ insert ++ lines.drop(3)).mkString("\n")
  }

  def singleLine =
    """#############
      |#...........#
      |###D#C#B#A###
      |  #########""".stripMargin

  def someInTheHallway =
    """#############
      |#.A.....C...#
      |###D#.#B#.###
      |  #########""".stripMargin

  def sampleInput = loadResource("sampleinput.txt")
  def puzzleInput = loadResource("puzzleinput.txt")
}
