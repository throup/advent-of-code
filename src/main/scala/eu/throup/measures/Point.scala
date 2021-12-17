package eu.throup.measures

case class Point(x: Int, y: Int) {
  def +(other: Point): Point = Point(x + other.x, y + other.y)
  def -(other: Point): Point = Point(x - other.x, y - other.y)

  def x_>=(p: Point): Boolean = x >= p.x
  def x_>(p: Point): Boolean = x > p.x
  def x_<(p: Point): Boolean = x < p.x
  def x_<=(p: Point): Boolean = x <= p.x
  def y_>=(p: Point): Boolean = y >= p.y
  def y_>(p: Point): Boolean = y > p.y
  def y_<(p: Point): Boolean = y < p.y
  def y_<=(p: Point): Boolean = y <= p.y
}

object Point {
  val Zero: Point = Point(0, 0)

  def apply(input: String): Point = {
    val coords: Array[Int] =
      input
        .split(",")
        .map(_.toInt)
    Point(coords(0), coords(1))
  }
  def apply(x: Double, y: Double): Point = Point(x.toInt, y.toInt)
}
