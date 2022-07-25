package eu.throup.aoc.year2015.day07

import eu.throup.aoc.DayXX
import cats.*
import cats.data.*
import cats.implicits.*

import scala.util.matching.Regex

object Day07 extends DayXX {
  override def part1(input: String): Int = {
    val instructions = parseInput(input)
    val commands = instructions.map(toCommand(_))
    0
  }

  override def part2(input: String): Int = {
    0
  }

  def parseInput(input: String): Seq[Instruction] =
    input.split("\n").map(Instruction(_))

  trait Command

  def toCommand(instruction: Instruction) = {
    instruction match {
      case Assign(a, target) => {
        val ke: Map[String, Eval[Int]] = ???

//        Eval.later()
        val out: (String, Eval[Int]) = ???
      }
      case Not(a, target)       => ???
      case And(a, b, target)    => ???
      case Or(a, b, target)     => ???
      case LShift(a, b, target) => ???
      case RShift(a, b, target) => ???
    }
  }

  sealed trait Value
  case class Pure(v: Int) extends Value
  case class Wire(k: String) extends Value
  object Value {
    def apply(s: String): Value = s.toIntOption.fold(Wire(s))(Pure(_))
  }

  type theMap = Map[String, Any]
  type it = theMap => Int

  sealed trait Instruction
  case class Assign(a: Value, target: Wire) extends Instruction
  case class Not(a: Value, target: Wire) extends Instruction
  case class And(a: Value, b: Value, target: Wire) extends Instruction
  case class Or(a: Value, b: Value, target: Wire) extends Instruction
  case class LShift(a: Value, b: Value, target: Wire) extends Instruction
  case class RShift(a: Value, b: Value, target: Wire) extends Instruction
  object Instruction {
    def apply(input: String): Instruction = {
      val regex: Regex = "^(\\S*?)\\s*(\\S*?)\\s*(\\S+) -> (.+)$".r
      val regex(a, inst, b, target) = input

      inst match {
        case "AND"    => And(Value(a), Value(b), Wire(target))
        case "OR"     => Or(Value(a), Value(b), Wire(target))
        case "LSHIFT" => LShift(Value(a), Value(b), Wire(target))
        case "RSHIFT" => RShift(Value(a), Value(b), Wire(target))
        case "NOT"    => Not(Value(b), Wire(target))
        case _        => Assign(Value(b), Wire(target))
      }
    }
  }

}
