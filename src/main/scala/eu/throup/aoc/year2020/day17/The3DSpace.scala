package eu.throup.aoc.year2020.day17

import eu.throup.aoc.year2020.day17.The3DSpace.*

case class The3DSpace(private val cubes: Space) {
  def isActive(x: Int, y: Int, z: Int): Boolean =
    getPoint(x, y, z)
      .map(_ == "#")
      .getOrElse(false)

  def getPlane(z: Int): Option[Plane] = ifInArray(z, cubes)
  def getLine(y: Int, z: Int): Option[Line] =
    getPlane(z).flatMap(ifInArray(y, _))
  def getPoint(x: Int, y: Int, z: Int): Option[Point] =
    getLine(y, z).flatMap(ifInArray(x, _))

  def activeCubes: Int =
    cubes.flatten.flatten.flatten
      .count(_ == '#')

  def rollToStage(num: Int): The3DSpace =
    if (num == 0) this else nextStage.rollToStage(num - 1)

  private def nextStage: The3DSpace =
    The3DSpace(
      zRange
        .map(z => {
          yRange
            .map(y => {
              xRange
                .map(x => {
                  val n = activeNeighbours(x, y, z)
                  if (isActive(x, y, z)) {
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
    )

  def activeNeighbours(x: Int, y: Int, z: Int): Int = {
    (z - 1 to z + 1)
      .flatMap(zz =>
        (y - 1 to y + 1).flatMap(yy =>
          (x - 1 to x + 1).map(xx => isActive(xx, yy, zz))
        )
      )
      .count(identity) - (if (isActive(x, y, z)) 1 else 0)
  }

  def zRange: Seq[Int] = Seq(-1) ++ cubes.indices ++ Seq(cubes.length)
  def yRange: Seq[Int] = Seq(-1) ++ cubes.head.indices ++ Seq(cubes.head.length)
  def xRange: Seq[Int] =
    Seq(-1) ++ cubes.head.head.indices ++ Seq(cubes.head.head.length)
}

object The3DSpace {
  type Space = Array[Plane]
  type Plane = Array[Line]
  type Line = Array[Point]
  type Point = String

  def apply(input: String): The3DSpace = The3DSpace(parseInput(input))

  private def parseInput(input: String) = {
    val the2DSpace = input.split("\n").map(_.split(""))
    Array(the2DSpace)
  }

  def ifInArray[A](i: Int, a: Array[A]): Option[A] =
    if (a.indices.contains(i)) Some(a(i)) else None
}
