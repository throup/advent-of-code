package eu.throup.advent2020

import scala.language.postfixOps
import scala.util.control.Breaks.{break, breakable}
import scala.util.matching.Regex

package object day9 {
  def part1(input: String, threshold: Int): Long = {
    val ints = mapToInt(input)
    keyFromInts(ints, threshold)
  }

  // ---

  def part2(input: String, threshold: Int): Long = {
    val ints = mapToInt(input)
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
    input.split("\n")
      .map(_.toLong)
  }
}
