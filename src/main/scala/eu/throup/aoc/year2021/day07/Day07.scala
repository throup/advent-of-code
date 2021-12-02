package eu.throup.aoc.year2021.day07

import eu.throup.aoc.DayXX

object Day07 extends DayXX {
  override def part1(input: String) =
    leastFuel(parseInput(input), identity)

  override def part2(input: String) =
    leastFuel(parseInput(input), triangleNumber)

  def leastFuel(crabs: Array[Int], summer: Int => Int) =
    (crabs.min to crabs.max)
      .map(fuelForPosition(_, crabs, summer))
      .min

  def fuelForPosition(
      pos: Int,
      crabs: Array[Int],
      summer: Int => Int
  ): Int =
    crabs
      .map(_ - pos)
      .map(_.abs)
      .map(summer)
      .sum

  def triangleNumber(a: Int): Int = (a * (a + 1)) / 2

  def parseInput(input: String): Array[Int] =
    input.trim
      .split(",")
      .map(_.toInt)
}
