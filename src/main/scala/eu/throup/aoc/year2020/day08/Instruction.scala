package eu.throup.aoc.year2020.day08

import eu.throup.aoc.year2020.day08.Day08.parseInstruction

import scala.language.postfixOps
import scala.util.matching.Regex

abstract class Instruction {
  val num: Int = 0
  var prev: Instruction = _
  var next: Instruction = _

  def act(): Int

  def jumpTo(num: Int): Instruction = {
    num.sign match {
      case 0  => this
      case 1  => next.jumpTo(num - 1)
      case -1 => prev.jumpTo(num + 1)
    }
  }

  var performed = false

  def perform(): Int = {
    if (performed) {
      0
    } else {
      performed = true
      act()
    }
  }
}

object Instruction {
  def apply(input: String): Instruction = {
    val (inst, valu) = parseInstruction(input)

    inst match {
      case "nop" => new NopInstruction
      case "acc" => new AccInstruction(valu)
      case "jmp" => new JmpInstruction(valu)

      case _ => new NopInstruction
    }
  }
}

class NopInstruction extends Instruction {
  override def act(): Int = next.perform()
}

class AccInstruction(override val num: Int) extends Instruction {
  override def act(): Int = num + next.perform()
}

class JmpInstruction(override val num: Int) extends Instruction {
  override def act(): Int = jumpTo(num).perform()
}

class TerInstruction extends Instruction {
  override def act(): Int = 0
}
