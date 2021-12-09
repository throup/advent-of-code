package eu.throup.aoc.year2021.day09

case class Grid(theMap: Map[Point, Int]) {
  lazy val lowPoints: Set[Point] =
    theMap
      .filter { (p, v) => adjacentValues(p).forall(_ > v) }
      .keys
      .toSet

  lazy val basins: Set[Set[Point]] = lowPoints.map(feedsInto(_))

  def feedsInto(p: Point): Set[Point] = {
    val direct =
      adjacent(p)
        .filter { (_, b) => b < 9 }
        .filter { (_, b) => b > theMap(p) }
        .map { (o, _) => o }
        .toSet
    (Set(p) ++ direct ++ direct.flatMap(feedsInto(_)))
  }

  def adjacentValues(p: Point): Seq[Int] =
    adjacentPoints(p).toSeq
      .map(theMap(_))

  def adjacent(p: Point): Seq[(Point, Int)] =
    adjacentPoints(p).toSeq
      .map(a => a -> theMap(a))

  def adjacentPoints(p: Point): Set[Point] =
    Set(
      p + Grid.Above,
      p + Grid.Below,
      p + Grid.Left,
      p + Grid.Right
    )
      .filter(theMap.contains(_))

  def values(points: Seq[Point]): Seq[Int] = points.map(theMap(_))
  def values(points: Set[Point]): Seq[Int] = values(points.toSeq)
}

object Grid {
  private val Above = Point(0, -1)
  private val Below = Point(0, 1)
  private val Left = Point(-1, 0)
  private val Right = Point(1, 0)

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
