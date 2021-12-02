package eu.throup.advent2020.day10

import org.scalatest.freespec.AnyFreeSpec

class day10Spec extends AnyFreeSpec {
  "day 10 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns an Long as output" in {
        val output: Long = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example 1" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == 35)
      }

      "Instruction example 2" in {
        val input = example2Input
        val output = part1(input)

        assert(output == 220)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 1885)
      }
    }
  }

  "day 10 - part 2" - {
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

        assert(output == 8)
      }

      "Instruction example 2" in {
        val input = example2Input
        val output = part2(input)

        assert(output == 19208)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 2024782584832L)
      }
    }
  }
  val exampleInput = "16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4"

  val example2Input = "28\n33\n18\n42\n31\n14\n46\n20\n48\n47\n24\n23\n49\n45\n19\n38\n39\n11\n1\n32\n25\n35\n8\n17\n7\n9\n4\n2\n34\n10\n3"

  val challengeInput = "80\n87\n10\n122\n57\n142\n134\n59\n113\n139\n101\n41\n138\n112\n46\n96\n43\n125\n36\n54\n133\n17\n42\n98\n7\n114\n78\n67\n77\n28\n149\n58\n20\n105\n31\n19\n18\n27\n40\n71\n117\n66\n21\n72\n146\n90\n97\n94\n123\n1\n119\n30\n84\n61\n91\n118\n2\n29\n104\n73\n13\n76\n24\n148\n68\n111\n131\n83\n49\n8\n132\n9\n64\n79\n124\n95\n88\n135\n3\n51\n39\n6\n60\n108\n14\n35\n147\n89\n34\n65\n50\n145\n128"
}
