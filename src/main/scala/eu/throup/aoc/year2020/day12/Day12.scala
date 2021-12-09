package eu.throup.aoc.year2020.day12

import eu.throup.aoc.DayXX

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

object Day12 extends DayXX {
  override def part1(input: String): Long = {
    val ship: Ship = new StandardShip
    ship.instruct(input)
    ship.distance
  }

  override def part2(input: String): Long = {
    val ship: Ship = new WaypointShip
    ship.instruct(input)
    ship.distance
  }

  implicit def bigDecimal2Long(x: BigDecimal): Long = {
    val rm: RoundingMode = RoundingMode.HALF_EVEN
    x.setScale(0, rm).toLong
  }
}
