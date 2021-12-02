package eu.throup.advent2020

import scala.language.postfixOps

package object day10 {
  def part1(input: String): Long = {
    val groups = differences(extractEnhancedInts(input))
      .groupBy(i => i)

    groups(3).length * groups(1).length
  }

  // ---

  def part2(input: String): Long = {
    val ints = extractEnhancedInts(input)

    var legs: Map[Long, Long] = Map()
    def myLegs(in: Long): Long = {
      if (!legs.contains(in)) {
        val routes = ints.filter(i => (1 to 3) contains (i - in))
        val calc = routes
          .map(myLegs)
          .sum
        val mip = math.max(1, calc)
        legs = legs.updated(in, mip)
      }
      legs(in)
    }
    myLegs(0)
  }

  // ---

  private def extractInts(input: String): Seq[Long] = {
    input.split("\n").map(_.toLong)
  }

  private def extractSortedInts(input: String): Seq[Long] = {
    extractInts(input).sortWith(_ < _)
  }

  private def extractEnhancedInts(input: String): Seq[Long] = {
    val sorted: Seq[Long] = extractSortedInts(input)
    Seq(0L) ++ sorted ++ Seq(sorted.last + 3)
  }

  private def differences(input: Seq[Long]): Seq[Long] = {
    (1 until input.length)
      .map(i => input(i) - input(i - 1))
  }
}
