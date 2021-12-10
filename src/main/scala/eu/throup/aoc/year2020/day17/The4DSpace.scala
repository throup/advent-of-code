package eu.throup.aoc.year2020.day17

import eu.throup.aoc.year2020.day17.The4DSpace.*

case class The4DSpace(private val cubes: Space) {
  def isActive(x: Int, y: Int, z: Int, w: Int): Boolean =
    getPoint(x, y, z, w)
      .map(_ == "#")
      .getOrElse(false)

  def getHyperPlane(w: Int): Option[HyperPlane] = ifInArray(w, cubes)
  def getPlane(z: Int, w: Int): Option[Plane] =
    getHyperPlane(w).flatMap(ifInArray(z, _))
  def getLine(y: Int, z: Int, w: Int): Option[Line] =
    getPlane(z, w).flatMap(ifInArray(y, _))
  def getPoint(x: Int, y: Int, z: Int, w: Int): Option[Point] =
    getLine(y, z, w).flatMap(ifInArray(x, _))

  def activeCubes: Int = {
    cubes.flatten.flatten.flatten.flatten
      .count(_ == '#')
  }

  def rollToStage(num: Int): The4DSpace =
    if (num == 0) this else nextStage.rollToStage(num - 1)

  private def nextStage: The4DSpace =
    The4DSpace(
      wRange
        .map(w => {
          zRange
            .map(z => {
              yRange
                .map(y => {
                  xRange
                    .map(x => {
                      val n = activeNeighbours(x, y, z, w)
                      if (isActive(x, y, z, w)) {
                        (2 to 3).contains(n)
                      } else {
                        3 == n
                      }
                    })
                    .map(if (_) "#" else ".")
                    .toArray
                })
                .toArray
            })
            .toArray
        })
        .toArray
    )

  def activeNeighbours(x: Int, y: Int, z: Int, w: Int): Int = {
    (w - 1 to w + 1)
      .flatMap(ww =>
        (z - 1 to z + 1).flatMap(zz =>
          (y - 1 to y + 1).flatMap(yy =>
            (x - 1 to x + 1).map(xx => isActive(xx, yy, zz, ww))
          )
        )
      )
      .count(i => i) - (if (isActive(x, y, z, w)) 1 else 0)
  }

  def wRange: Seq[Int] = Seq(-1) ++ cubes.indices ++ Seq(cubes.length)
  def zRange: Seq[Int] = Seq(-1) ++ cubes.head.indices ++ Seq(cubes.head.length)
  def yRange: Seq[Int] =
    Seq(-1) ++ cubes.head.head.indices ++ Seq(cubes.head.head.length)
  def xRange: Seq[Int] =
    Seq(-1) ++ cubes.head.head.head.indices ++ Seq(cubes.head.head.head.length)
}

object The4DSpace {
  type Space = Array[HyperPlane]
  type HyperPlane = Array[Plane]
  type Plane = Array[Line]
  type Line = Array[Point]
  type Point = String

  def apply(input: String): The4DSpace = The4DSpace(parseInput(input))

  private def parseInput(input: String) = {
    val the2DSpace = input.split("\n").map(_.split(""))
    Array(Array(the2DSpace))
  }

  def ifInArray[A](i: Int, a: Array[A]): Option[A] =
    if (a.indices.contains(i)) Some(a(i)) else None
}
