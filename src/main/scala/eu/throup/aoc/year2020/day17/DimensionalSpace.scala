package eu.throup.aoc.year2020.day17

import eu.throup.aoc.year2020.day17.DimensionalSpace.Point

class DimensionalSpace[P <: DimensionalPoint[P]](cubesMap: Map[P, Point]) {
  def rollToStage(num: Int): DimensionalSpace[P] =
    if (num == 0) this else nextStage.rollToStage(num - 1)

  lazy val nextStage: DimensionalSpace[P] =
    map(p => if (shouldBeActive(p)) "#" else ".")

  def map(f: P => Point): DimensionalSpace[P] = DimensionalSpace(
    allPoints.map(p => p -> f(p)).toMap
  )

  def shouldBeActive(point: P): Boolean = {
    val n = activeNeighbours(point)
    if (isActive(point)) {
      (2 to 3).contains(n)
    } else {
      3 == n
    }
  }

  def activeNeighbours(point: P): Int =
    point.adjacent.toSeq
      .map(isActive(_))
      .count(identity) - (if (isActive(point)) 1 else 0)

  def isActive(point: P): Boolean = {
    cubesMap
      .get(point)
      .map(_ == "#")
      .getOrElse(false)
  }

  lazy val activeCubes: Int = allCubes.count(_ == "#")

  lazy val allCubes: Iterable[Point] = cubesMap.values

  lazy val allPoints: Set[P] =
    cubesMap.keySet
      .flatMap(_.adjacent.toSeq)
}

object DimensionalSpace {
  type Point = String

  def _3d(input: String): DimensionalSpace[A3dPoint] =
    DimensionalSpace(
      arrayToMap3(input)
    )

  def _4d(input: String): DimensionalSpace[A4dPoint] =
    DimensionalSpace(
      arrayToMap4(input)
    )

  private def arrayToMap3(input: String): Map[A3dPoint, Point] = {
    val points = Array(toArray2(input))
    (for {
      z: Int <- points.indices
      y: Int <- points.head.indices
      x: Int <- points.head.head.indices
      point = A3dPoint(x, y, z)
    } yield point -> points(z)(y)(x)).toMap
  }

  private def arrayToMap4(input: String): Map[A4dPoint, Point] = {
    val points = Array(Array(toArray2(input)))
    (for {
      w: Int <- points.indices
      z: Int <- points.head.indices
      y: Int <- points.head.head.indices
      x: Int <- points.head.head.head.indices
      point = A4dPoint(x, y, z, w)
    } yield point -> points(w)(z)(y)(x)).toMap
  }

  private def toArray2(input: String): Array[Array[Point]] =
    input.split("\n").map(_.split(""))
}
