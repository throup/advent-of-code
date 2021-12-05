package eu.throup.aoc.year2021.day05

case class Point(x: Int, y: Int)

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
