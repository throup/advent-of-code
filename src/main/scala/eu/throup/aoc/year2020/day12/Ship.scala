package eu.throup.aoc.year2020.day12

import eu.throup.measures.Angle
import eu.throup.measures.Vector

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

abstract class Ship {
  protected var x = BigDecimal(0)
  protected var y = BigDecimal(0)
  protected var dir: Angle = Angle.degrees(0)

  def instruct(input: String): Unit

  def distance: BigDecimal = x.abs + y.abs

  protected def parseInstructions(input: String): Array[(String, Long)] =
    input.split("\n").map(parseInstuction)

  private def parseInstuction(d: String): (String, Long) = (
    d.substring(0, 1),
    d.substring(1).toLong
  )
}

class StandardShip extends Ship {
  override def instruct(input: String): Unit = {
    for (inst <- parseInstructions(input)) {
      inst match {
        case ("N", dist) => y += dist
        case ("S", dist) => y -= dist
        case ("E", dist) => x += dist
        case ("W", dist) => x -= dist
        case ("L", ang)  => dir += Angle.degrees(ang)
        case ("R", ang)  => dir -= Angle.degrees(ang)
        case ("F", dist) => {
          x += dir.cos * dist
          y += dir.sin * dist
        }
      }
    }
  }
}

class WaypointShip extends Ship {
  var waypoint: Vector = Vector(10, 1)

  override def instruct(input: String): Unit = {
    for (inst <- parseInstructions(input)) {
      inst match {
        case ("N", dist) => waypoint += Vector(0, dist)
        case ("S", dist) => waypoint -= Vector(0, dist)
        case ("E", dist) => waypoint += Vector(dist, 0)
        case ("W", dist) => waypoint -= Vector(dist, 0)
        case ("L", ang)  => waypoint += Angle.degrees(ang)
        case ("R", ang)  => waypoint -= Angle.degrees(ang)
        case ("F", dist) => {
          x += waypoint.x * dist
          y += waypoint.y * dist
        }
      }
    }
  }
}
