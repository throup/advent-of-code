package eu.throup.aoc.year2021.day08

import eu.throup.aoc.DayXX

type DigInput = Set[Char]

object Day08 extends DayXX {
  override def part1(input: String) =
    parseInput(input)
      .map(
        calculateForLine(
          _,
          _.filter(
            Set(Digit._1, Digit._4, Digit._7, Digit._8).contains(_)
          ).length
        )
      )
      .sum

  override def part2(input: String) =
    parseInput(input)
      .map(
        calculateForLine(
          _,
          _.map(_.value)
            .reduce(_ * 10 + _)
        )
      )
      .sum

  private def calculateForLine(
      line: (Set[DigInput], Seq[DigInput]),
      aggregator: Seq[Digit] => Int
  ) = {
    val (inputs, message) = line
    aggregator(digitsForLine(inputs, message))
  }

  def digitsForLine(
      inputs: Set[DigInput],
      message: Seq[DigInput]
  ): Seq[Digit] =
    message.map(
      DigitFinder(inputs)
        .toDigits(_)
    )

  def parseInput(input: String): Array[(Set[DigInput], Seq[DigInput])] =
    input.trim
      .split("\n")
      .map(parseLine(_))

  private def parseLine(lineInput: String): (Set[DigInput], Seq[DigInput]) = {
    val p = lineInput.trim
      .split("\\|")
      .map(parseItems(_))
    (p(0).toSet, p(1).toSeq)
  }

  private def parseItems(itemsInput: String): Array[DigInput] =
    itemsInput.trim
      .split("\\s+")
      .map(parseItem(_))

  private def parseItem(itemInput: String): DigInput = {
    itemInput.trim
      .toCharArray
      .toSet
      .asInstanceOf[DigInput]
  }
}
