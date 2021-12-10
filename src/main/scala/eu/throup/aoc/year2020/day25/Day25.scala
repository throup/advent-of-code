package eu.throup.aoc.year2020.day25

import eu.throup.aoc.DayXX

import scala.collection.mutable.Map

object Day25 extends DayXX {
  override def part1(input: String): Long = {
    val subject = 7
    val parts = input.split("\n")

    val doorKey = parts(0).toLong
    val cardKey = parts(1).toLong

    var loop = 0L
    var result = 1L
    while (result != doorKey && result != cardKey) {
      loop += 1
      result = doTransform(result, subject)
    }

    if (result == cardKey) {
      transform(doorKey, loop)
    } else {
      transform(cardKey, loop)
    }
  }

  override def part2(input: String): Long = 0

  val results: Map[String, Long] = Map.empty
  def transform(subject: Long, size: Long, value: Long = 1): Long = {
    if (size >= 1) {
      val key = subject.toString + ":" + value.toString
      if (!results.contains(key)) {
        results(key) = doTransform(subject, value)
      }
      transform(subject, size - 1, results(key))
    } else {
      value
    }
  }

  def doTransform(subject: Long, value: Long): Long = {
    val product = value * subject
    product % 20201227
  }
}
