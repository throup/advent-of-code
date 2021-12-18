package eu.throup.aoc.year2015.day03

import eu.throup.aoc.DayXX
import eu.throup.measures.Point

object Day03 extends DayXX {
  override def part1(input: String) = {
    val directions = parseInput(input)
    actorPerform(directions).size
  }

  override def part2(input: String) = {
    val directions = parseInput(input)
    val dirdirs = directions.zipWithIndex.partition(p => (p._2 % 2) == 0)

    (
      actorPerform(dirdirs._1.map(_._1)) ++ actorPerform(dirdirs._2.map(_._1))
    ).size
  }

  private def actorPerform(directions: Array[Direction]) =
    directions.indices
      .map(i => directions.take(i + 1))
      .map(_.map(_.p).reduce(_ + _))
      .toSet + Point.Zero

  def parseInput(input: String): Array[Direction] =
    input.trim.toCharArray
      .map {
        case '^' => Direction.North
        case '>' => Direction.East
        case 'v' => Direction.South
        case '<' => Direction.West
      }
}

enum Direction(val p: Point) {
  case North extends Direction(Point(0, 1))
  case East extends Direction(Point(1, 0))
  case South extends Direction(Point(0, -1))
  case West extends Direction(Point(-1, 0))
}
