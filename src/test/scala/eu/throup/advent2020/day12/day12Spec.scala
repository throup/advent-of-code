package eu.throup.advent2020.day12

import org.scalatest.freespec.AnyFreeSpec

class day12Spec extends AnyFreeSpec {
  "day 12 - part 1" - {
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

        assert(output == 25)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 1838)
      }
    }
  }

  "day 12 - part 2" - {
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

        assert(output == 286)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output > 71606)
        assert(output < 174910)
        assert(output == 89936)
      }
    }
  }

  val exampleInput = "F10\nN3\nF7\nR90\nF11"

  val challengeInput = "N3\nF18\nL180\nF40\nN3\nR90\nS5\nR90\nN4\nF24\nR90\nE5\nF36\nR180\nW3\nW4\nF63\nN4\nW1\nN1\nE1\nL90\nW1\nN2\nE2\nS2\nF39\nW4\nS3\nF93\nN1\nF83\nS1\nR90\nW3\nR90\nW4\nL90\nF53\nS4\nF4\nL90\nW3\nF83\nL180\nW2\nL90\nW2\nL90\nW1\nN3\nF63\nR90\nN2\nN3\nE4\nF10\nS3\nE4\nR90\nF11\nL90\nR90\nS2\nW2\nF100\nW5\nR270\nF40\nS5\nL90\nE2\nL90\nE2\nL180\nN5\nF81\nN4\nE4\nL180\nF38\nW2\nF22\nW5\nN5\nE1\nN2\nW4\nN2\nF68\nN1\nF2\nS1\nF47\nW5\nF80\nN3\nE3\nS2\nL180\nF87\nL180\nE4\nL90\nE2\nS3\nL180\nE2\nL90\nW2\nN4\nF21\nS4\nW5\nF70\nF4\nN2\nF14\nE2\nS3\nR90\nW3\nN2\nE3\nS1\nF85\nR90\nE1\nF80\nL90\nF100\nR90\nW1\nR180\nS4\nF58\nL90\nN3\nR90\nE1\nF42\nE3\nF93\nS3\nR90\nW2\nN3\nL90\nW3\nW2\nN2\nW1\nS4\nR180\nN5\nR180\nF52\nN5\nF20\nL180\nE5\nR90\nW2\nS4\nE1\nS3\nF75\nR90\nF49\nL180\nN3\nF31\nS3\nE3\nS5\nL180\nN3\nE2\nR270\nW5\nN3\nW5\nN3\nL270\nF54\nR90\nW5\nF73\nS3\nW2\nR90\nN2\nR90\nS5\nR90\nW4\nS2\nL90\nF3\nS2\nR90\nF76\nS3\nF56\nL90\nF5\nN1\nR180\nE3\nN2\nF20\nE2\nL180\nF38\nR180\nW4\nR90\nS3\nN5\nE5\nF26\nS2\nL180\nE4\nR90\nF52\nN3\nL90\nN5\nE4\nF63\nL90\nF48\nW5\nF29\nN1\nE3\nL90\nN5\nL90\nS3\nF8\nN2\nR90\nE4\nS2\nE2\nF10\nW2\nL90\nN2\nR90\nF2\nE2\nN4\nR90\nF74\nW3\nW5\nS2\nR90\nN3\nL90\nE3\nF58\nN4\nE5\nS4\nE3\nF72\nL180\nE3\nS2\nL90\nW4\nS1\nF14\nW1\nN1\nE3\nW4\nL90\nN1\nF97\nR90\nN4\nE3\nF95\nF95\nL90\nS4\nF55\nR90\nW2\nN1\nR90\nF16\nL90\nS5\nF4\nR90\nF24\nS4\nE2\nR90\nW5\nE1\nL270\nF12\nL90\nF100\nW1\nS5\nW2\nS3\nF95\nL90\nF44\nN5\nF79\nS4\nR180\nE2\nS1\nF40\nR90\nW2\nR90\nF67\nS5\nF15\nL90\nN4\nL90\nS5\nE1\nR90\nN3\nW5\nN4\nL270\nF61\nL90\nE1\nL90\nE1\nF38\nE2\nF19\nW2\nL90\nS4\nR180\nW4\nF59\nN1\nF26\nN1\nW5\nF7\nN4\nF72\nE2\nR90\nF59\nN1\nF58\nN5\nF13\nN2\nF2\nS2\nW1\nF85\nR270\nS2\nF17\nR90\nF96\nS2\nL90\nE1\nN4\nF9\nR270\nF58\nN1\nL90\nW2\nS2\nF73\nW1\nS2\nF20\nE2\nS4\nF94\nL180\nF27\nS2\nF48\nN1\nL270\nS2\nF77\nE3\nF10\nW3\nL270\nS4\nF53\nF66\nE5\nS2\nF33\nS5\nL90\nW3\nS3\nE3\nR90\nE1\nF62\nS1\nL90\nS3\nE3\nN1\nS1\nE5\nS2\nF66\nN4\nN1\nW4\nF84\nR180\nF23\nF20\nE1\nS3\nR90\nE2\nF48\nF89\nL90\nF97\nR180\nN3\nF62\nL90\nN5\nF28\nW5\nN4\nL180\nN4\nW1\nN3\nL90\nF95\nN1\nW5\nR180\nN5\nF34\nS1\nW2\nN4\nF3\nS2\nE1\nR90\nE2\nF36\nS4\nE5\nF42\nW1\nL180\nS1\nF74\nF38\nN4\nR270\nN3\nW2\nS4\nL180\nF26\nS4\nF51\nR90\nF83\nR90\nF9\nS2\nW1\nF99\nS4\nW1\nF84\nW1\nR180\nF59\nW5\nR90\nF75\nS1\nF34\nE4\nN3\nL90\nF43\nW5\nN1\nR90\nF59\nW1\nN3\nW4\nS2\nF36\nN5\nW4\nE2\nF96\nR180\nF44\nR90\nF12\nE5\nF24\nW3\nF39\nS2\nL180\nW3\nW4\nF70\nN4\nE4\nF36\nE2\nN1\nF30\nL90\nS2\nF81\nR270\nR90\nF66\nW1\nL90\nW2\nF98\nS1\nE1\nL90\nE3\nN2\nF100\nW3\nN3\nR90\nF88\nE4\nL180\nF52\nL90\nE4\nF76\nW2\nL90\nE3\nF72\nS3\nL180\nF12\nF34\nE5\nF90\nS5\nW5\nE1\nN5\nL180\nE5\nF84\nE5\nE3\nL90\nE3\nF14\nL90\nW3\nL90\nS1\nL90\nW2\nF54\nR90\nS2\nF73\nS4\nE1\nS1\nF55\nE5\nN4\nR180\nL180\nN4\nR90\nF91\nL180\nF5\nE2\nN1\nW2\nF27\nW2\nS5\nR90\nS3\nF39\nS3\nW2\nF59\nF83\nW3\nE3\nE4\nL90\nS1\nR90\nE4\nF81\nE4\nR90\nW5\nF74\nW3\nE3\nF30\nL180\nS2\nE3\nF33\nS3\nR90\nF22\nS5\nF97\nS1\nE2\nF50\nE2\nF19\nE3\nL90\nL90\nS5\nW3\nF80\nF33\nE1\nR90\nN3\nL90\nF70\nL180\nW4\nN2\nR180\nS2\nF38\nS3\nF7\nR90\nE1\nN5\nF86\nW4\nF49\nW4\nF51\nS4\nF47\nR90\nW3\nR180\nR180\nW1\nF98\nS1\nW3\nS4\nL90\nF76\nE1\nF76\nR180\nS4\nR180\nW3\nF26\nN5\nF35\nS2\nF94\nF24\nN2\nF45\nE1\nL90\nF32\nS1\nR180\nF78\nF84\nL90\nN2\nF42\nR90\nF72\nS1\nE3\nN2\nW1\nF23\nE2\nF69\nL90\nF29\nR90\nS5\nW5\nL90\nW1\nS2\nE1\nF96\nS5\nR180\nF26\nS5\nW1\nS3\nF38\nS1\nE2\nS5\nW2\nS5\nF52\nL90\nF11\nE3\nR90\nE4\nF6\nL90\nR90\nW1\nR90\nE3\nF1\nE4\nN3\nE5\nR90\nN2\nR180\nW2\nN5\nF46\nN3\nE5\nF83\nR90\nF42\nS3\nR90\nN5\nF10"
}
