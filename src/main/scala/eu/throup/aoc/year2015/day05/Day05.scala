package eu.throup.aoc.year2015.day05

import eu.throup.aoc.DayXX
import cats.*
import cats.data.*
import cats.implicits.*

object Day05 extends DayXX {
  override def part1(input: String): Int = {
    val results: Seq[String] = for {
      input <- parseInput(input)
      validation = validateItem1(input)
      if validation.isRight
    } yield input
    results.length
  }

  def validateItem1(item: String): Either[Failur, String] = for {
    _ <- threeVowelsTest(item)
    _ <- doubleLetterTest(item)
    _ <- badFragmentTest(item)
  } yield item

  def threeVowelsTest(input: String): Either[Failur, String] = if (
    input.count("aeiou".contains(_)) >= 3
  ) input.asRight
  else InsufficientVowels.asLeft

  def doubleLetterTest(input: String): Either[Failur, String] = if (
    (input.dropRight(1) zip input.tail).exists(p => p._1 == p._2)
  ) input.asRight
  else DoubleLetter.asLeft

  def badFragmentTest(input: String): Either[Failur, String] = if (
    Seq("ab", "cd", "pq", "xy").exists(input.contains(_))
  ) BadFragment.asLeft
  else input.asRight

  override def part2(input: String): Int = {
    val d = "fff"
    val results: Seq[String] = for {
      input <- parseInput(input)
      validation = validateItem2(input)
      if validation.isRight
    } yield input
    results.length
  }

  def validateItem2(item: String): Either[Failur, String] = for {
    _ <- nonOverlappingPairTest(item)
    _ <- withAGapTest(item)
    _ = println(s"Accepting $item")
  } yield item

  def nonOverlappingPairTest(input: String): Either[Failur, String] = {
    def it(im: String): Boolean = {
      if (im.length < 4) false
      else if (im.drop(2).contains(im.take(2))) true
      else it(im.tail)
    }

    val test = it(input)

    if (test) input.asRight else BadPair.asLeft
  }

  def withAGapTest(input: String): Either[Failur, String] = if (
    (input.dropRight(2) zip input.tail.tail).exists(p => p._1 == p._2)
  ) input.asRight
  else DoubleLetter.asLeft

  def parseInput(input: String): Seq[String] = input.split("\n")

  sealed trait Failur
  object InsufficientVowels extends Failur
  object DoubleLetter extends Failur
  object BadFragment extends Failur
  object BadPair extends Failur
}
