package eu.throup.aoc.year2021.day05

case class LineSegment(p1: Point, p2: Point) {
  val ydelta = p2.y - p1.y
  val xdelta = p2.x - p1.x

  val xMin = p1.x min p2.x
  val xMax = p1.x max p2.x
  val yMin = p1.y min p2.y
  val yMax = p1.y max p2.y

  // Used in the equation: -ny + -mx = c
  val m: Double = if (xdelta == 0) -1 else ydelta.toDouble / xdelta
  val n: Double = if (xdelta == 0) 0 else -1
  val c: Double = -n * p1.y - m * p1.x

  val points: Set[Point] =
    if (xdelta == 0)
      then(yMin to yMax)
        .map(y => Point((n * y + c), y))
        .toSet
    else
      (xMin to xMax)
        .map(x => Point(x, (m * x + c)))
        .toSet

  def intersects(other: LineSegment): Set[Point] =
    points.intersect(other.points)
}

object LineSegment {
  def apply(input: String): LineSegment = {
    val points: Array[Point] =
      input
        .split(" *-> *")
        .map(Point(_))
    LineSegment(points(0), points(1))
  }
}
