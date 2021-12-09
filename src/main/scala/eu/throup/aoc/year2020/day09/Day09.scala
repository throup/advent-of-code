package eu.throup.aoc.year2020.day09

import eu.throup.aoc.DayXX

import scala.util.control.Breaks.{break, breakable}

object Day09 extends DayXX {
  override def part1(input: String): Long = {
    val (threshold, rest) = splitInput(input)
    val ints = mapToInt(rest)
    keyFromInts(ints, threshold)
  }

  // ---

  override def part2(input: String): Long = {
    val (threshold, rest) = splitInput(input)
    val ints = mapToInt(rest)
    val key = keyFromInts(ints, threshold)

    var sol: Seq[Long] = Seq.empty
    breakable(
      for (i <- 2 until ints.length) {
        for (j <- 0 until ints.length - i) {
          sol = ints.slice(j, i + j)
          val sum = sol.sum
          if (sum == key)
            break
        }
      }
    )
    sol.min + sol.max
  }

  // ---

  private def keyFromInts(ints: Array[Long], threshold: Int): Long = {
    (threshold until ints.length)
      .map(i => (ints.slice(i - threshold, i), ints(i)))
      .map({ case (prev: Array[Long], subject: Long) =>
        val a = prev.toSet
        val b = prev.map(subject - _).toSet
        val d = a intersect b
        (d.size, subject)
      })
      .find({ case (u, v) => u == 0 })
      .map({ case (u, v) => v })
      .getOrElse(0)
  }

  private def mapToInt(input: String) = {
    input
      .split("\n")
      .map(_.toLong)
  }

  def splitInput(input: String): (Int, String) = {
    val splits = input.split(":")
    (splits(0).toInt, splits(1))
  }
}
