package eu.throup.aoc.year2021.day09

// TODO: Merge with the implementation in day05
case class Point(x: Int, y: Int) {
  def +(other: Point): Point = Point(x + other.x, y + other.y)
  def -(other: Point): Point = Point(x - other.x, y - other.y)
}

object Point {
  def apply(input: String): Point = {
    val coords: Array[Int] =
      input
        .split(",")
        .map(_.toInt)
    Point(coords(0), coords(1))
  }
  def apply(x: Double, y: Double): Point = Point(x.toInt, y.toInt)
}
