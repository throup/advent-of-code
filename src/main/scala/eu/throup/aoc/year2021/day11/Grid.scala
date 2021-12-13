package eu.throup.aoc.year2021.day11

import eu.throup.measures.Point

// TODO: identify common elements with day09
case class Grid(theMap: Map[Point, Int], flashes: Long = 0) {

  lazy val points = theMap.keys.toSet
  lazy val collectFlashes: Grid =
    Grid(
      theMap.mapValues(i => if (i > 9) 0 else i).toMap,
      flashes + theMap.count { case (p, i) => i > 9 }
    )

  def map(f: Int => Int) =
    Grid(
      theMap.mapValues(f).toMap,
      flashes
    )

  def map(f: (Point, Int) => Int) =
    Grid(
      theMap.map { case (p, i) => (p, f(p, i)) },
      flashes
    )

  def filter(f: Int => Boolean) =
    Grid(
      theMap.filter { case (_, i) => f(i) },
      flashes
    )

  def count(f: Int => Boolean): Int =
    theMap.count { case (_, i) => f(i) }

  def flash(p: Point): Grid =
    map((o: Point, i: Int) =>
      if ((adjacentPoints(p) + p).contains(o)) i + 1 else i
    )

  def adjacentPoints(p: Point): Set[Point] =
    Set(
      p + Point(-1, -1),
      p + Point(0, -1),
      p + Point(1, -1),
      p + Point(-1, 0),
      p + Point(1, 0),
      p + Point(-1, 1),
      p + Point(0, 1),
      p + Point(1, 1)
    ).filter(theMap.contains(_))
}

object Grid {
  def apply(input: String): Grid = {
    Grid(
      input.trim
        .split("\n")
        .map(
          _.toCharArray
            .map(_ - 48) // converts numerical char to int
        )
    )
  }

  def apply(input: Array[Array[Int]]): Grid =
    Grid(
      input.indices
        .flatMap(y => input.head.indices.map(x => Point(x, y) -> input(y)(x)))
        .toMap
    )
}
