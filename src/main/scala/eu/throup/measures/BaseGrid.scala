package eu.throup.measures

// TODO: can this be a single class, with an added "what is adjacent?" strategy?
trait BaseGrid[A] {
  type G <: BaseGrid[A]
  def constructMe: Map[Point, A] => G

  def theMap: Map[Point, A]

  lazy val points: Set[Point] = theMap.keys.toSet

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

  def values(points: Seq[Point]): Seq[A] = points.map(theMap(_))
  def values(points: Set[Point]): Seq[A] = values(points.toSeq)

  def count(f: A => Boolean): Int =
    theMap.count { case (_, i) => f(i) }

  def map(f: A => A): G =
    constructMe(theMap.mapValues(f).toMap)

  def map(f: (Point, A) => A): G =
    constructMe(theMap.map { case (p, i) => (p, f(p, i)) })

  def filter(f: A => Boolean): G =
    constructMe(theMap.filter { case (_, i) => f(i) })

  def filter(f: (Point, A) => Boolean): G =
    constructMe(theMap.filter { case (p, i) => f(p, i) })
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
