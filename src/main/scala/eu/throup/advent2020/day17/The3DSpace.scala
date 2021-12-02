package eu.throup.advent2020.day17

class The3DSpace(private val cubes: Array[Array[Array[String]]]) {
  def isActive(x: Int, y: Int, z: Int): Boolean = {
    if (cubes.indices.contains(z)) {
      if (cubes(z).indices.contains(y)) {
        if (cubes(z)(y).indices.contains(x)) {
          cubes(z)(y)(x) == "#"
        } else {
          false
        }
      } else {
        false
      }
    } else {
      false
    }
  }

  def activeCubes: Int = {
    cubes.flatten
      .flatten
      .flatten
      .count(_ == '#')
  }

  def rollToStage(num: Int): The3DSpace = {
    val space = if (num == 0) this else nextStage.rollToStage(num - 1)
    space
  }

  private def nextStage: The3DSpace = {
    val newCubes = zRange.map(z => {
      yRange.map(y => {
        xRange.map(x => {
          val n = activeNeighbours(x, y, z)
          if (isActive(x, y, z)) {
            (2 to 3).contains(n)
          } else {
            3 == n
          }
        }).map(if (_) "#" else ".").toArray
      }).toArray
    }).toArray
    val space = new The3DSpace(newCubes)
    space
  }

  def activeNeighbours(x: Int, y: Int, z: Int): Int = {
    (z - 1 to z + 1).flatMap(
      zz => (y - 1 to y + 1).flatMap(
        yy => (x - 1 to x + 1).map(
          xx => isActive(xx, yy, zz)
        )
      )
    ).count(i => i) - (if (isActive(x, y, z)) 1 else 0)
  }

  def zRange: Seq[Int] = Seq(-1) ++ cubes.indices ++ Seq(cubes.length)
  def yRange: Seq[Int] = Seq(-1) ++ cubes(0).indices ++ Seq(cubes(0).length)
  def xRange: Seq[Int] = Seq(-1) ++ cubes(0)(0).indices ++ Seq(cubes(0)(0).length)
}

object The3DSpace {
  def apply(input: String): The3DSpace = new The3DSpace(parseInput(input))

  private def parseInput(input: String) = {
    val the2DSpace = input.split("\n").map(_.split(""))
    Array(the2DSpace)
  }
}

class The4DSpace(private val cubes: Array[Array[Array[Array[String]]]]) {
  def isActive(x: Int, y: Int, z: Int, w: Int): Boolean = {
    if (cubes.indices.contains(w)) {
      if (cubes(w).indices.contains(z)) {
        if (cubes(w)(z).indices.contains(y)) {
          if (cubes(w)(z)(y).indices.contains(x)) {
            cubes(w)(z)(y)(x) == "#"
          } else {
            false
          }
        } else {
          false
        }
      } else {
        false
      }
    } else {
      false
    }
  }

  def activeCubes: Int = {
    cubes.flatten
      .flatten
      .flatten
      .flatten
      .count(_ == '#')
  }

  def rollToStage(num: Int): The4DSpace = {
    val space = if (num == 0) this else nextStage.rollToStage(num - 1)
    space
  }

  private def nextStage: The4DSpace = {
    val newCubes =
      wRange.map(w => {
        zRange.map(z => {
          yRange.map(y => {
            xRange.map(x => {
              val n = activeNeighbours(x, y, z, w)
              if (isActive(x, y, z, w)) {
                (2 to 3).contains(n)
              } else {
                3 == n
              }
            }).map(if (_) "#" else ".").toArray
          }).toArray
        }).toArray
      }).toArray
    val space = new The4DSpace(newCubes)
    space
  }

  def activeNeighbours(x: Int, y: Int, z: Int, w: Int): Int = {
    (w - 1 to w + 1).flatMap(
      ww => (z - 1 to z + 1).flatMap(
        zz => (y - 1 to y + 1).flatMap(
          yy => (x - 1 to x + 1).map(
            xx => isActive(xx, yy, zz, ww)
          )
        )
      )
    ).count(i => i) - (if (isActive(x, y, z, w)) 1 else 0)
  }

  def wRange: Seq[Int] = Seq(-1) ++ cubes.indices ++ Seq(cubes.length)
  def zRange: Seq[Int] = Seq(-1) ++ cubes(0).indices ++ Seq(cubes(0).length)
  def yRange: Seq[Int] = Seq(-1) ++ cubes(0)(0).indices ++ Seq(cubes(0)(0).length)
  def xRange: Seq[Int] = Seq(-1) ++ cubes(0)(0)(0).indices ++ Seq(cubes(0)(0)(0).length)
}

object The4DSpace {
  def apply(input: String): The4DSpace = new The4DSpace(parseInput(input))

  private def parseInput(input: String) = {
    val the2DSpace = input.split("\n").map(_.split(""))
    Array(Array(the2DSpace))
  }
}
