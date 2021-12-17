package eu.throup.aoc.year2021.day17

import eu.throup.aoc.DayXX
import eu.throup.measures.Point

import scala.annotation.tailrec
import scala.collection.MapView

object Day17 extends DayXX {
  override def part1(input: String) = {
    solve(
      PointRange(input)
    ).toSeq
      .sortBy(_.highest.y)
      .reverse
      .head
      .highest
      .y
  }

  override def part2(input: String) =
    solve(PointRange(input)).size

  def solve(target: PointRange): Set[LaunchResult] =
    solveWithExpandingSearchWindow(
      target.toSet,
      target
    )

  @tailrec
  def solveWithExpandingSearchWindow(
      window: PointSet,
      target: PointRange,
      known: Map[Point, LaunchResult] = Map()
  ): Set[LaunchResult] = {
    if (window.isEmpty) known.values.collect { case h: Hit => h }.toSet
    else {
      val newResults = searchInWindow(window, target)
      val collected: Map[Point, LaunchResult] = known ++ newResults
      val nextWindow: Set[Point] = extendWindow(
        newResults.filter {
          case (_, h: Hit) => true
          case _           => false
        }.keySet
      )
        .filter(!collected.keySet.contains(_))
      solveWithExpandingSearchWindow(nextWindow, target, collected)
    }
  }

  def searchInWindow(
      velocities: PointSet,
      target: PointRange
  ): Map[Point, LaunchResult] = {
    velocities
      .map(velocity => velocity -> testLaunch(velocity, target))
      .toMap
  }

  @tailrec
  def testLaunch(
      initialVelocity: Point,
      target: PointRange,
      updatedVelocity: Option[Point] = None,
      position: Point = Point.Zero,
      highest: Point = Point.Zero
  ): LaunchResult = {
    val velocity = updatedVelocity.getOrElse(initialVelocity)
    if (target.contains(position)) Hit(initialVelocity, highest)
    else if (target.yMin > position.y)
      if (target x_> position) Undershoot(initialVelocity, highest)
      else Overshoot(initialVelocity, highest)
    else {
      val newPosition = position + velocity
      testLaunch(
        initialVelocity,
        target,
        Some(applyDrag(velocity)),
        newPosition,
        if (newPosition y_> highest) newPosition else highest
      )
    }
  }

  def applyDrag(velocity: Point) =
    velocity + (
      if (velocity.x > 0) Point(-1, -1)
      else if (velocity.x < 0) Point(1, -1)
      else Point(0, -1)
    )

  def extendWindow(window: PointSet): PointSet = {
    if (window.isEmpty) window
    else {
      PointRange(
        Point(
          window.xMin / 2,
          window.yMin + 1
        ),
        Point(
          window.xMax,
          (if (window.yMax > 0) 2 * window.yMax
           else if (window.yMax < 0) window.yMax / 2
           else 0) + 1
        )
      ).toSet
    }
  }
}
