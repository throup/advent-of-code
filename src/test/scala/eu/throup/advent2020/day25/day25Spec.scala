package eu.throup.advent2020.day25

import org.scalatest.freespec.AnyFreeSpec

class day25Spec extends AnyFreeSpec {
  "day 25 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns an Long as output" in {
        val output: Long = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == 14897079)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 7032853)
      }
    }
  }

  "day 25 - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInput)
      }
      "returns an Int as output" in {
        val output: Long = part2(exampleInput)
      }
    }

    "examples" - {
      "Instruction example 1" in {
        val input = exampleInput
        val output = part2(input)

        assert(output == 0)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 0)
      }
    }
  }

  "transform" - {
    "Example door" in {
      val subject = 7
      val size = 11

      assert(transform(subject, size) == 17807724)
    }
    "Example card" in {
      val subject = 7
      val size = 8

      assert(transform(subject, size) == 5764801)
    }
    "Example door public key" in {
      val subject = 17807724
      val size = 8

      assert(transform(subject, size) == 14897079)
    }
    "Example card public key" in {
      val subject = 5764801
      val size = 11

      assert(transform(subject, size) == 14897079)
    }
  }

  def exampleInput = "17807724\n5764801"

  def challengeInput = "7573546\n17786549"
}
