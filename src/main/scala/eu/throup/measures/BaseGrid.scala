package eu.throup.measures

import eu.throup.measures.BaseGrid.seqToMap

// TODO: can this be a single class, with an added "what is adjacent?" strategy?
trait BaseGrid[A] {
  type G <: BaseGrid[A]
  def constructMe: Map[Point, A] => G

  def theMap: Map[Point, A]

  lazy val points: Set[Point] = theMap.keys.toSet

  lazy val minX: Int = points.map(_.x).min
  lazy val maxX: Int = points.map(_.x).max
  lazy val minY: Int = points.map(_.y).min
  lazy val maxY: Int = points.map(_.y).max

  lazy val width: Int = maxX - minX
  lazy val height: Int = maxY - minY

  // Note minPoint and maxPoint represent two corners of the grid.
  // It is not guaranteed that these points are defined in theMap.
  lazy val minPoint = Point(minX, minY)
  lazy val maxPoint = Point(maxX, maxY)

  def adjacentPoints(p: Point): Set[Point]

  def adjacentValues(p: Point): Seq[A] =
    adjacentPoints(p).toSeq
      .map(theMap(_))

  def adjacent(p: Point): G =
    constructMe(
      adjacentPoints(p).toSeq
        .map(o => o -> theMap(o))
        .toMap
    )

  def apply(point: Point): A = theMap(point)

  def valuesOrElse(points: Seq[Point], orElse: A): Seq[A] =
    points.map(theMap.getOrElse(_, orElse))
  def values(points: Seq[Point]): Seq[A] = points.map(theMap(_))
  def values(points: Set[Point]): Seq[A] = values(points.toSeq)

  def count(f: A => Boolean): Int =
    theMap.count { case (_, i) => f(i) }

  def map(f: A => A): G =
    constructMe(theMap.mapValues(f).toMap)

  def map(f: (Point, A) => A): G =
    constructMe(theMap.map { case (p, i) => (p, f(p, i)) })

  def mapFull(f: (Point, A) => (Point, A)): G =
    constructMe(theMap.map { case (p, i) => f(p, i) })

  def filter(f: A => Boolean): G =
    constructMe(theMap.filter { case (_, i) => f(i) })

  def filter(f: (Point, A) => Boolean): G =
    constructMe(theMap.filter { case (p, i) => f(p, i) })

  def crop(x0: Int, x1: Int, y0: Int, y1: Int): G =
    filter((p: Point, a: A) =>
      (
        p.x >= x0 && p.x <= x1 && p.y >= y0 && p.y <= y1
      )
    )

  def +(t: (Point, A)): G = constructMe(theMap + t)
  def set(p: Point, a: A): G = this.+((p, a))

  def ++(ts: Seq[(Point, A)]): G = constructMe(theMap ++ ts)
  def ++(ts: Set[(Point, A)]): G = ++(ts.toSeq)
  def ++(o: G): G = ++(o.theMap.toSeq)
}

object BaseGrid {
  def seqToMap[A](array: Seq[Seq[A]]): Map[Point, A] =
    (for {
      y <- array.indices
      x <- array.head.indices
      point = Point(x, y)
    } yield Point(x, y) -> array(y)(x)).toMap
}

case class StraightGrid[A](theMap: Map[Point, A]) extends BaseGrid[A] {
  override type G = StraightGrid[A]
  override def constructMe: Map[Point, A] => StraightGrid[A] = StraightGrid(_)

  def adjacentPoints(p: Point): Set[Point] =
    Set(
      p + Point(0, -1),
      p + Point(-1, 0),
      p + Point(1, 0),
      p + Point(0, 1)
    )
      .filter(theMap.contains(_))
}

object StraightGrid {
  def apply[A](seqSeq: Seq[Seq[A]]): StraightGrid[A] =
    StraightGrid(seqToMap(seqSeq))
}

case class DiagonalGrid[A](theMap: Map[Point, A]) extends BaseGrid[A] {
  override type G = DiagonalGrid[A]
  override def constructMe: Map[Point, A] => DiagonalGrid[A] = DiagonalGrid(_)

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

object DiagonalGrid {
  def apply[A](seqSeq: Seq[Seq[A]]): DiagonalGrid[A] =
    DiagonalGrid(seqToMap(seqSeq))
}
