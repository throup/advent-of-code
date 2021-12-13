package eu.throup.aoc.year2021.day13

case class Paper(points: Set[Point]) {
  def apply(fold: Fold): Paper =
    Paper(points.map(fold(_)))

  def apply(folds: Seq[Fold]): Paper =
    if (folds.isEmpty) this
    else this(folds.head)(folds.tail)

  override lazy val toString: String = toArray.map(_.mkString).mkString("\n")

  lazy val size: Int = points.size

  lazy val toArray: Array[Array[Char]] = {
    val maxX = points.map(_.x).max
    val maxY = points.map(_.y).max
    (0 to maxY).map(y => (0 to maxX).map(x => charAt(x, y)).toArray).toArray
  }

  def charAt(p: Point): Char =
    if (points.contains(p)) 'X' else '_'

  def charAt(x: Int, y: Int): Char =
    charAt(Point(x, y))
}

object Paper {
  def apply(seq: Seq[Point]): Paper = Paper(seq.toSet)
}
