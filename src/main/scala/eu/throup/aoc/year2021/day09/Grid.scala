package eu.throup.aoc.year2021.day09

import eu.throup.measures.{BaseGrid, Point, StraightGrid}

type Grid = StraightGrid[Int]
extension (grid: Grid) {
  def lowPoints: Set[Point] =
    grid
      .filter { (p, v) => grid.adjacentValues(p).forall(_ > v) }
      .points

  def basins: Set[Set[Point]] = lowPoints.map(feedsInto(_))

  def feedsInto(p: Point): Set[Point] = {
    val direct =
      grid.adjacent(p)
        .filter { (_, b) => b < 9 }
        .filter { (_, b) => b > grid.theMap(p) }
        .points
    (Set(p) ++ direct ++ direct.flatMap(feedsInto(_)))
  }
}

object Grid {
  def apply(input: String): Grid = {
    Grid(
      input.trim
        .split("\n")
        .map(_.toCharArray.map(_ - 48))
    )
  }

  def apply(input: Array[Array[Int]]): Grid = {
    Grid(
      input.indices
        .flatMap(y => input.head.indices.map(x => Point(x, y) -> input(y)(x)))
        .toMap
    )
  }

  def apply(input: Map[Point, Int]) = StraightGrid(input)
}
