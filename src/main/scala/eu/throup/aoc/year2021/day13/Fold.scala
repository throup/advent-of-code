package eu.throup.aoc.year2021.day13

import eu.throup.measures.Point

sealed trait Fold {
  def apply(p: Point): Point
  def reflect(n: Int, m: Int): Int = if (n < m) n else 2 * m - n
}

case class FoldY(y: Int) extends Fold {
  override def apply(p: Point): Point =
    Point(p.x, reflect(p.y, y))
}
case class FoldX(x: Int) extends Fold {
  override def apply(p: Point): Point =
    Point(reflect(p.x, x), p.y)
}

object Fold {
  def apply(input: String): Fold = {
    val split = input.trim.split("=")
    (split(0), split(1).toInt) match {
      case ("fold along x", x) => FoldX(x)
      case ("fold along y", y) => FoldY(y)
    }
  }
}
