package eu.throup.aoc.year2021.day05

import eu.throup.aoc.DayXX

import scala.annotation.tailrec

object Day05 extends DayXX {
  override def part1(input: String) =
    intersections(
      parseInput(input)
        .filter(l => l.m == 0 || l.n == 0)
    ).size

  override def part2(input: String) =
    intersections(
      parseInput(input)
    ).size

  @tailrec
  def intersections(
      lineSegments: Set[LineSegment],
      points: Set[Point] = Set()
  ): Set[Point] =
    if (lineSegments.isEmpty) points
    else {
      val first = lineSegments.head
      val rest = lineSegments.tail
      val newPoints = rest.flatMap(first.intersects(_))
      intersections(rest, points ++ newPoints)
    }

  def parseInput(input: String): Set[LineSegment] =
    input.trim
      .split("\n")
      .map(LineSegment(_))
      .toSet
}
