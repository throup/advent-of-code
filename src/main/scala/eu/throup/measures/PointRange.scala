package eu.throup.measures

case class PointRange(p1: Point, p2: Point) {
  lazy val xMin = p1.x min p2.x
  lazy val xMax = p1.x max p2.x
  lazy val yMin = p1.y min p2.y
  lazy val yMax = p1.y max p2.y

  lazy val xRange: Range = (xMin to xMax)
  lazy val yRange: Range = (yMin to yMax)

  def x_>=(p: Point): Boolean = xMax >= p.x
  def x_>(p: Point): Boolean = xMin > p.x
  def x_<(p: Point): Boolean = xMax < p.x
  def x_<=(p: Point): Boolean = xMin <= p.x
  def y_>=(p: Point): Boolean = yMax >= p.y
  def y_>(p: Point): Boolean = yMin > p.y
  def y_<(p: Point): Boolean = yMax < p.y
  def y_<=(p: Point): Boolean = yMin <= p.y

  def contains(p: Point): Boolean = x_<=(p) && x_>=(p) && y_<=(p) && y_>=(p)

  lazy val toSet: PointSet =
    (for {
      x <- xRange
      y <- yRange
    } yield Point(x, y)).toSet
}
object PointRange {
  def apply(p1: (Int, Int), p2: (Int, Int)): PointRange =
    PointRange(Point(p1._1, p1._2), Point(p2._1, p2._2))
  def apply(x1: Int, x2: Int, y1: Int, y2: Int): PointRange =
    apply((x1, y1), (x2, y2))

  def apply(input: String): PointRange = {
    val a = input.trim
      .split(":")
      .reverse
      .head
      .split(",")
      .map(parseInner)
      .transpose
      .map { case Array(x, y) =>
        Point(x, y)
      }
    PointRange(a(0), a(1))
  }

  private def parseInner(input: String) = {
    input
      .split("=")
      .reverse
      .head
      .split("\\.\\.")
      .map(_.trim.toInt)
  }
}
