package eu.throup.aoc.year2020.day17

sealed trait DimensionalPoint[A <: DimensionalPoint[A]] {
  def adjacent: Set[A]
}

case class A3dPoint(x: Int, y: Int, z: Int) extends DimensionalPoint[A3dPoint] {
  override lazy val adjacent: Set[A3dPoint] =
    (z - 1 to z + 1)
      .flatMap(zz =>
        (y - 1 to y + 1).flatMap(yy =>
          (x - 1 to x + 1).map(xx => A3dPoint(xx, yy, zz))
        )
      )
      .toSet
}

case class A4dPoint(x: Int, y: Int, z: Int, w: Int)
    extends DimensionalPoint[A4dPoint] {
  override lazy val adjacent: Set[A4dPoint] =
    (w - 1 to w + 1)
      .flatMap(ww =>
        (z - 1 to z + 1).flatMap(zz =>
          (y - 1 to y + 1).flatMap(yy =>
            (x - 1 to x + 1).map(xx => A4dPoint(xx, yy, zz, ww))
          )
        )
      )
      .toSet
}
