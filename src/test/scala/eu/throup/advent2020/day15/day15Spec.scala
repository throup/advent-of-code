package eu.throup.advent2020.day15

import org.scalatest.freespec.AnyFreeSpec

class day15Spec extends AnyFreeSpec {
  val exampleInputs = Map(
    "0,3,6" -> 436,
    "1,3,2" -> 1,
    "2,1,3" -> 10,
    "1,2,3" -> 27,
    "2,3,1" -> 78,
    "3,2,1" -> 438,
    "3,1,2" -> 1836
  )

  val example2Inputs = Map(
    "0,3,6" -> 175594,
    "1,3,2" -> 2578,
    "2,1,3" -> 3544142,
    "1,2,3" -> 261214,
    "2,3,1" -> 6895259,
    "3,2,1" -> 18,
    "3,1,2" -> 362
  )

  val challengeInput = "12,1,16,3,11,0"

  "day 15 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInputs.head._1)
      }
      "returns an Long as output" in {
        val output: Long = part1(exampleInputs.head._1)
      }
    }

    "examples" - {
      "Instruction examples" - {
        exampleInputs.foreach(
          {case (input, expected) =>
            "For input: " + input in {
              val output = part1(input)
              assert(output == expected)
            }
          }
        )
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 1696)
      }
    }
  }

  "day 15 - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInputs.head._1)
      }
      "returns an Int as output" in {
        val output: Long = part2(exampleInputs.head._1)
      }
    }

    "examples" - {
      "Instruction examples" - {
        example2Inputs.foreach(
          {case (input, expected) =>
            "For input: " + input in {
              val output = part2(input)
              assert(output == expected)
            }
          }
        )
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 37385)
      }
    }
  }
}
