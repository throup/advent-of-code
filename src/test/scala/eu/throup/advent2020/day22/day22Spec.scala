package eu.throup.advent2020.day22

import org.scalatest.freespec.AnyFreeSpec

class day22Spec extends AnyFreeSpec {
  "day 22 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns an BigInt as output" in {
        val output: BigInt = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == 306)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 36257)
      }
    }
  }

  "day 22 - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInput)
      }
      "returns an BigInt as output" in {
        val output: BigInt = part2(exampleInput)
      }
    }

    "examples" - {
      "Instruction example 1" in {
        val input = exampleInput
        val output = part2(input)

        assert(output == 291)
      }

      "Recursive example" in {
        val input = recursiveInput
        val output = part2(input)

        assert(output == 105)
      }

      "Deeper example" in {
        val input = deeperInput
        val output = part2(input)

        assert(output == 335)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 33304)
      }
    }
  }

  def exampleInput = "Player 1:\n9\n2\n6\n3\n1\n\nPlayer 2:\n5\n8\n4\n7\n10"

  def recursiveInput = "Player 1:\n43\n19\n\nPlayer 2:\n2\n29\n14"

  def deeperInput = "Player 1:\n1\n1\n1\n1\n2\n1\n1\n1\n1\n1\n1\n1\n\nPlayer 2:\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n3\n1"

  def challengeInput = "Player 1:\n31\n24\n5\n33\n7\n12\n30\n22\n48\n14\n16\n26\n18\n45\n4\n42\n25\n20\n46\n21\n40\n38\n34\n17\n50\n\nPlayer 2:\n1\n3\n41\n8\n37\n35\n28\n39\n43\n29\n10\n27\n11\n36\n49\n32\n2\n23\n19\n9\n13\n15\n47\n6\n44"
}
