package eu.throup.advent2020.day23

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class day23Spec extends AnyFreeSpec with Matchers {
  "day 23 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns an String as output" in {
        val output: String = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == "67384529")
      }

      "My example" in {
        val input = "123456789"
        val output = part1(input)

        assert(output == "46853792")
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == "75893264")
      }
    }
  }

  "day 23 - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInput, 1)
      }
      "returns a Long as output" in {
        val output: Long = part2(exampleInput, 1)
      }
    }

    "examples" - {
      "1 round" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 1)

          assert(output == 20)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 1)

          assert(output == 56)
        }
      }
      "10 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 10)

          assert(output == 12)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 10)

          assert(output == 60)
        }
      }
      "100 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 100)

          assert(output == 12)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 100)

          assert(output == 60)
        }
      }
      "1.000 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 1000)

          assert(output == 12)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 1000)

          assert(output == 60)
        }
      }
      "10.000 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 10000)

          assert(output == 12)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 10000)

          assert(output == 60)
        }
      }
      "100.000 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 100000)

          assert(output == 12)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 100000)

          assert(output == 60)
        }
      }
      "1.000.000 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 1000000)

          assert(output == 126)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 1000000)

          assert(output == 375)
        }
      }
      "10.000.000 rounds" - {
        "Instruction example 1" in {
          val input = exampleInput
          val output = part2(input, 10000000)

          assert(output == 149245887792L)
        }

        "Task set" in {
          val input = challengeInput
          val output = part2(input, 10000000)

          assert(output == 38162588308L)
        }
      }
    }
  }

  for (i <- 0 to 100) {

  }

  def calcNewMin(sum: Double, min: Long, max: Long): Long = {
    val pairWithMax = (min to max).map(x => (x, (sum - max - x) / max / x))
    val filteredMaxPairs = pairWithMax.filter(p => p._2 <= 1).map(_._1)
    val d = pairWithMax.find(p => p._2 <= 1).map(_._1).getOrElse(0)
    1L
  }

  def exampleInput = "389125467"

  def challengeInput = "974618352"
}
