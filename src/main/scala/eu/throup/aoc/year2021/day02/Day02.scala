package eu.throup.aoc.year2021.day02

import eu.throup.aoc.year2021.DayXX

import scala.runtime.Tuple2Zipped

object Day02 extends DayXX {
  override def part1(input: String) = {
    val parsed = parseInput(input)
    position(parsed) * naiveDepth(parsed)
  }

  override def part2(input: String) = {
    val parsed = parseInput(input)
    position(parsed) * aimedDepth(parsed)
  }

  private def position(parsed: Array[Array[String]]) =
    parsed.map { case Array(dir, amount) =>
      dir match {
        case "forward" => amount.toLong
        case _         => 0
      }
    }.sum

  private def naiveDepth(parsed: Array[Array[String]]) =
    parsed.map { case Array(dir, amount) =>
      dir match {
        case "up"   => -amount.toLong
        case "down" => amount.toLong
        case _      => 0
      }
    }.sum

  private def aimedDepth(parsed: Array[Array[String]]) = {
    val aimDelta = parsed.map { case Array(dir, amount) =>
      dir match {
        case "up"   => -amount.toLong
        case "down" => amount.toLong
        case _      => 0
      }
    }

    val aimCumulative: Seq[Long] =
      aimDelta.indices
        .map(i => aimDelta.take(i + 1))
        .map(_.sum)

    (parsed zip aimCumulative).map { case (Array(dir, amount), aim) =>
      dir match {
        case "forward" => amount.toLong * aim
        case _         => 0
      }
    }.sum
  }

  def parseInput(input: String): Array[Array[String]] =
    input.trim
      .split("\n")
      .map(_.split("\\s"))
}
