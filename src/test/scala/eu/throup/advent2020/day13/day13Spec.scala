package eu.throup.advent2020.day13

import org.scalatest.freespec.AnyFreeSpec

class day13Spec extends AnyFreeSpec {
  "day X - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns an Long as output" in {
        val output: BigInt = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == 295)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 3997)
      }
    }
  }

  "day X - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInput)
      }
      "returns an Int as output" in {
        val output: BigInt = part2(exampleInput)
      }
    }

    "examples" - {
      "Simpleff 1" in {
        val mr = modInverse(7, 15)

        assert(mr == 13)
      }
      "Simple example 1" in {
        val input = "1\n3,5"
        val output = part2(input)

        assert(output == 9)
      }

      "Simple example 2" in {
        val input = "1\n5,3"
        val output = part2(input)

        assert(output == 5)
      }
      "Simple example 3" in {
        val input = "1\n3,5,7"
        val output = part2(input)

        assert(output == 54)
      }
      "Simple example 4" in {
        val input = "1\n7,5,3"
        val output = part2(input)

        assert(output == 49)
      }

      "Instruction example 1" in {
        val input = exampleInput
        val output = part2(input)

        assert(output == 1068781)
      }

      "Instruction example 2" in {
        val input = example2Input
        val output = part2(input)

        assert(output == 3417)
      }

      "Instruction example 3" in {
        val input = example3Input
        val output = part2(input)

        assert(output == 754018)
      }

      "Instruction example 4" in {
        val input = example4Input
        val output = part2(input)

        assert(output == 779210)
      }

      "Instruction example 5" in {
        val input = example5Input
        val output = part2(input)

        assert(output == 1261476)
      }

      "Instruction example 6" in {
        val input = example6Input
        val output = part2(input)

        assert(output == 1202161486)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output < 670107664530791L)
        assert(output == 500033211739354L)
      }
    }
  }

  val exampleInput = "939\n7,13,x,x,59,x,31,19"
  val example2Input = "939\n17,x,13,19"
  val example3Input = "939\n67,7,59,61"
  val example4Input = "939\n67,x,7,59,61"
  val example5Input = "939\n67,7,x,59,61"
  val example6Input = "939\n1789,37,47,1889"

  val challengeInput = "1003240\n19,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,787,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,x,x,x,x,x,x,23,x,x,x,x,x,29,x,571,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17"
}
