package eu.throup.advent2020.day17

import org.scalatest.freespec.AnyFreeSpec

class day17Spec extends AnyFreeSpec {
  "day 17 - part 1" - {
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

        assert(output == 112)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 336)
      }
    }
  }

  "day 17 - part 2" - {
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

        assert(output == 848)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 2620)
      }
    }
  }

  "Sample runs" - {
    val space = The3DSpace(exampleInput)

    "Stage 0" in {
      assert(space.activeCubes == 5)
    }

    "Stage 1" in {
      assert(space.rollToStage(1).activeCubes == 11)
    }

    "Stage 2" in {
      assert(space.rollToStage(2).activeCubes == 21)
    }

    "Stage 3" in {
      assert(space.rollToStage(3).activeCubes == 38)
    }
  }

  def exampleInput = ".#.\n..#\n###"

  def challengeInput = "#####..#\n#..###.#\n###.....\n.#.#.#..\n##.#..#.\n######..\n.##..###\n###.####"
}
