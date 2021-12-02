package eu.throup.aoc.year2020.day01

import eu.throup.aoc.DayXX

import scala.util.control.Breaks.{break, breakable}

object Day01 extends DayXX {
  private val Log = Logger(this.getClass)

  override def part1(input: String) = {
    val parsed = parseInput(input)
    val sorted: Seq[Int] = parsed.sortWith(_ < _)

    var frontPointer = 0
    var backPointer = sorted.length - 1
    while (sorted(frontPointer) + sorted(backPointer) != 2020) {
      while (sorted(frontPointer) + sorted(backPointer) > 2020) {
        backPointer -= 1
      }
      frontPointer += 1
    }

    sorted(frontPointer) * sorted(backPointer)
  }

  override def part2(input: String) = {
    val parsed = parseInput(input)
    val sorted: Seq[Int] = parsed.sortWith(_ < _)

    var frontPointer = 0
    var midPointer = 1
    var backPointer = sorted.length - 1

    def isValid(p: Int) = {
      p >= 0 && p < sorted.length
    }
    while (
      isValid(frontPointer) && isValid(midPointer) && isValid(
        backPointer
      ) && (sorted(frontPointer) + sorted(midPointer) + sorted(
        backPointer
      ) != 2020)
    ) {
      breakable {
        for (i <- (frontPointer + 1) until sorted.length - 2) {
          for (j <- (frontPointer + 2) until sorted.length - 1) {
            backPointer = j
            midPointer = i
            if (
              isValid(frontPointer) && isValid(midPointer) && isValid(
                backPointer
              ) && (sorted(frontPointer) + sorted(midPointer) + sorted(
                backPointer
              ) == 2020)
            )
              break
          }
        }
        frontPointer += 1
      }
    }
    Log.info("frontPointer = {} => {}", frontPointer, sorted(frontPointer))
    Log.info("midPointer = {} => {}", midPointer, sorted(midPointer))
    Log.info("backPointer = {} => {}", backPointer, sorted(backPointer))
    sorted(frontPointer) * sorted(midPointer) * sorted(backPointer)
  }

  def parseInput(input: String): Seq[Int] =
    input.trim
      .split("\n")
      .map(_.toInt)
}
