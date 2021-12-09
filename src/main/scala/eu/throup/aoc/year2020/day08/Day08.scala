package eu.throup.aoc.year2020.day08

import eu.throup.aoc.DayXX

object Day08 extends DayXX {
  override def part1(input: String): Int = {
    val splits = input.split("\n")
    val instructions = toInstructions(splits)

    instructions(0).perform()
  }

  // ---

  override def part2(input: String): Int = {
    val splits = input.split("\n")

    var sol = -1
    for (i <- splits.indices) {
      val cop = splits.clone()
      cop(i) = switchUp(cop(i))

      val instructions = toInstructions(cop)
      val meep = instructions(0).perform()

      if (instructions.last.performed)
        sol = meep
    }

    sol
  }

  // ---

  private def switchUp(input: String): String = {
    val (inst, valu) = parseInstruction(input)

    val newInst = inst match {
      case "nop" => "jmp"
      case "acc" => "acc"
      case "jmp" => "nop"
    }

    newInst + " " + valu
  }

  private def toInstructions(splits: Array[String]): Array[Instruction] = {
    val instructions =
      splits
        .map(Instruction(_))

    for (i <- instructions.indices) {
      if (i > 0) {
        instructions(i).prev = instructions(i - 1)
      }
      if (i < instructions.length - 1) {
        instructions(i).next = instructions(i + 1)
      }
    }

    val fin = new TerInstruction
    instructions.last.next = fin

    instructions
  }

  def parseInstruction(input: String): (String, Int) = {
    val pattern = "(\\w+)\\s*([+-]?\\d+)".r
    val theMatch = pattern.findFirstMatchIn(input).get
    (
      theMatch.group(1),
      theMatch.group(2).toInt
    )
  }
}
