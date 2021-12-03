package eu.throup.aoc.year2021.day03

import eu.throup.aoc.year2021.DayXX

import scala.annotation.tailrec

object Day03 extends DayXX {
  override def part1(input: String) = {
    val parsed = parseInput(input)

    val gamma = greekFilter(parsed, _ > _)
    val epsilon = greekFilter(parsed, _ <= _)

    gamma * epsilon
  }

  override def part2(input: String) = {
    val parsed = parseInput(input)

    val oxygen = gasFilter(parsed, _ > _)
    val co2 = gasFilter(parsed, _ <= _)

    oxygen * co2
  }

  def greekFilter(
      seqOfDigits: Seq[Seq[Int]],
      test: (Int, Int) => Boolean
  ): Int = binarySeqToInt(
    filterByPosition(
      seqOfDigits,
      o => if (test(o.getOrElse(0, 0), o.getOrElse(1, 0))) 0 else 1
    )
  )

  def gasFilter(
      seqOfDigits: Seq[Seq[Int]],
      test: (Int, Int) => Boolean
  ) = binarySeqToInt(
    filterByFrequency(
      seqOfDigits,
      o => if (test(o.getOrElse(0, 0), o.getOrElse(1, 0))) 0 else 1
    )
  )

  def filterByPosition(
      seqOfDigits: Seq[Seq[Int]],
      extractor: Map[Int, Int] => Int
  ): Seq[Int] =
    seqOfDigits.head.indices
      .map(applyExtractor(seqOfDigits, extractor, _))

  @tailrec
  def filterByFrequency(
      seqOfDigits: Seq[Seq[Int]],
      extractor: Map[Int, Int] => Int,
      pos: Int = 0
  ): Seq[Int] =
    if (seqOfDigits.length == 1)
      seqOfDigits.head
    else {
      filterByFrequency(
        seqOfDigits.filter(
          _(pos) == applyExtractor(seqOfDigits, extractor, pos)
        ),
        extractor,
        pos + 1
      )
    }

  def applyExtractor(
      seqOfDigits: Seq[Seq[Int]],
      extractor: Map[Int, Int] => Int,
      pos: Int
  ): Int =
    extractor(
      seqOfDigits
        .map(_(pos))
        .groupBy(identity)
        .mapValues(_.size)
        .toMap
    )

  def binarySeqToInt(seq: Seq[Int]): Int =
    Integer.parseInt(seq.mkString, 2)

  def binaryStringToSeq(binary: String): Seq[Int] =
    binary.toCharArray
      .map(_ - 48) // converts 0/1 char to int

  def parseInput(input: String): Seq[Seq[Int]] =
    input.trim
      .split("\n")
      .map(binaryStringToSeq(_))
}
