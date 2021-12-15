package eu.throup.aoc.year2021.day15

import eu.throup.aoc.DayXX
import eu.throup.measures.{Point, StraightGrid}

import scala.annotation.tailrec
import scala.collection.mutable
import scala.jdk.CollectionConverters.*
import scala.util.Try

object Day15 extends DayXX {
  override def part1(input: String) =
    solve(
      parseInputNodeGrid(input)
    )

  override def part2(input: String) =
    solve(
      expandGrid(
        parseInputNodeGrid(input)
      )
    )

  def solve(grid: StraightGrid[Node]): Int =
    findTheBest(
      grid + (grid.maxPoint -> grid(grid.maxPoint).newBest(0)),
      Set(),
      Seq(grid.maxPoint),
      grid.minPoint
    )(grid.minPoint)

  @tailrec
  def findTheBest(
      grid: StraightGrid[Node],
      visited: Set[Point],
      queue: Seq[Point],
      target: Point
  ): Map[Point, Int] = {
    if (visited.contains(target)) grid.theMap.map { case (p, n) =>
      p -> n.bestPath.getOrElse(0)
    }
    else {
      val (point :: rest) = sortQueue(grid, queue)
      val node: Node = grid(point)

      val eligiblePoints = grid
        .adjacentPoints(point)
        .filter(!visited.contains(_))
        .filter(n => grid(n).bestPath.fold(true)(_ > node.offer.get))

      findTheBest(
        grid ++ eligiblePoints.map(p => p -> grid(p).newBest(node.offer.get)),
        visited + point,
        rest ++ eligiblePoints,
        target
      )
    }
  }

  def sortQueue(grid: StraightGrid[Node], queue: Seq[Point]): Seq[Point] =
    queue
      .map(p => p -> grid(p).offer)
      .sortBy(_._2.get) // If we don't have an offer, something has gone wrong.
      .map(_._1)

  def expandGrid(grid: StraightGrid[Node]): StraightGrid[Node] = {
    val expandRight = expandGridRight(grid, grid, 4)
    expandGridBelow(expandRight, expandRight, 4)
  }

  def expandGridBelow(
      grid: StraightGrid[Node],
      fragment: StraightGrid[Node],
      remaining: Int = 0
  ): StraightGrid[Node] =
    expandGridFragment(grid, fragment, Point(0, fragment.height + 1), remaining)

  def expandGridRight(
      grid: StraightGrid[Node],
      fragment: StraightGrid[Node],
      remaining: Int = 0
  ): StraightGrid[Node] =
    expandGridFragment(grid, fragment, Point(fragment.width + 1, 0), remaining)

  @tailrec
  def expandGridFragment(
      grid: StraightGrid[Node],
      fragment: StraightGrid[Node],
      offset: Point,
      remaining: Int
  ): StraightGrid[Node] = {
    if (remaining == 0) grid
    else {
      val next = increaseGrid(fragment, offset)
      expandGridFragment(grid ++ next, next, offset, remaining - 1)
    }
  }

  def increaseGrid(
      grid: StraightGrid[Node],
      offset: Point = Point.Zero
  ): StraightGrid[Node] =
    grid.mapFull((p, v) => {
      (p + offset) -> (v match {
        case Node(9, bestPath)     => Node(1, bestPath)
        case Node(value, bestPath) => Node(value + 1, bestPath)
      })
    })

  def parseInputNodeGrid(input: String): StraightGrid[Node] = {
    StraightGrid(
      input.trim
        .split("\n")
        .toSeq
        .map(
          _.toCharArray.map(_ - 48).map(Node(_)).toSeq
        )
    )
  }
}
