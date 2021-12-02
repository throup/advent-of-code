package eu.throup.advent2020

package object day3 {
  def part1(input: String): Int = {
    val slope = new SkiSlope(input)
    slope.trees(3, 1)
  }

  def part2(input: String): Long = {
    val slope = new SkiSlope(input)

    val a = slope.trees(1, 1).toLong
    val b = slope.trees(3, 1).toLong
    val c = slope.trees(5, 1).toLong
    val d = slope.trees(7, 1).toLong
    val e = slope.trees(1, 2).toLong

    a * b * c * d * e
  }
}
