package eu.throup.advent2020

import eu.throup.measures.Angle
import eu.throup.measures.Vector

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

package object day12 {
  def part1(input: String): Long = {
    val ship: Ship = new StandardShip
    ship.instruct(input)
    ship.distance
  }

  // ---

  def part2(input: String): Long = {
    val ship: Ship = new WaypointShip
    ship.instruct(input)
    ship.distance
  }

  // ---

  implicit def bigDecimal2Long(x: BigDecimal): Long = {
    val rm: RoundingMode = RoundingMode.HALF_EVEN
    x.setScale(0, rm).toLong
  }
}
