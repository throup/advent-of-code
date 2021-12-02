package eu.throup.advent2020

package object day24 {
  def part1(input: String): Long = {
    getTiles2(input)
      .flatten
      .sum
  }


  // ---

  private def getTiles(input: String): Map[(Int, Int), Int] = {
    parseLines(input)
      .map(findCoordinates)
      .groupBy(identity)
      .mapValues(_.length)
      .mapValues(_ % 2)
      .toMap
  }

  private def getTiles2(input: String): Array[Array[Int]] = {
    val tiles: Map[(Int, Int), Int] = getTiles(input)

    val k: Map[Int, Map[(Int, Int), Int]] = tiles.groupBy(e => e._1._1)

    val kmin = k.keys.min
    val kmax = k.keys.max

    val kkk: Array[Map[(Int, Int), Int]] = (kmin to kmax).toArray.map(k.get).map(_.getOrElse(Map.empty))

    val lll: Array[Map[Int, Int]] = kkk.map(m => m.map(me => me._1._2 -> me._2))

    val llmins = lll.map(m => m.keys.min)
    val llmin = llmins.min
    val llmaxs = lll.map(m => m.keys.max)
    val llmax = llmaxs.max

    val mmm: Array[Array[Int]] = lll.map((im: Map[Int, Int]) => {
      val inclusive = (llmin to llmax).toArray
      val value = inclusive.map(im.get)
      val value1 = value.map(_.getOrElse(0))
      value1
    })

    mmm
  }

  def findCoordinates(inst: Array[String]): (Int, Int) = {
    inst.map(findSingleCoordinate)
      .reduce((a, b) => (a._1 + b._1, a._2 + b._2))
  }

  private def findSingleCoordinate(i: String): (Int, Int) = {
    i match {
      case "e" => (1, 0)
      case "w" => (-1, 0)
      case "ne" => (1, 1)
      case "nw" => (0, 1)
      case "se" => (0, -1)
      case "sw" => (-1, -1)
    }
  }

  private def parseLines(input: String) = {
    input.split("\n").map(parseLine)
  }

  private def parseLine(input: String) = {
    input
      .replaceAll("se", "T")
      .replaceAll("sw", "U")
      .replaceAll("ne", "V")
      .replaceAll("nw", "W")
      .split("")
      .map {
        case "T" => "se"
        case "U" => "sw"
        case "V" => "ne"
        case "W" => "nw"
        case c => c
      }
  }


  def part2(input: String): Long = {
    var tiles = getTiles2(input)

    (0 until 100).foreach(_ => {
      tiles = flipTiles(tiles)
    })

    tiles.flatten.sum
  }

  private def flipTiles(tiles: Array[Array[Int]]) = {
    val width = tiles.length
    val height = tiles.head.length

    val newTiles = (-1 to width).toArray.map(i => {
      (-1 to height).toArray.map(j => {
        val e = (i + 1, j)
        val w = (i - 1, j)
        val ne = (i + 1, j + 1)
        val nw = (i, j + 1)
        val se = (i, j - 1)
        val sw = (i - 1, j - 1)

        val neighbours = List(
          e,
          w,
          ne,
          nw,
          se,
          sw
        )

        val mm: List[Int] = neighbours.map({ case (x, y) => tileAt(tiles, x, y) })
        val mn: Int = mm.sum

        tileAt(tiles, i, j) match {
          case 0 => if (mn == 2) 1 else 0
          case 1 => if (mn == 0 || mn > 2) 0 else 1
        }
      })
    })
    newTiles
  }

  def tileAt(source: Array[Array[Int]], x: Int, y: Int): Int = {
    if (source.indices.contains(x)) {
      if (source(x).indices.contains(y)) {
        source(x)(y)
      } else 0
    } else 0
  }
  // ---
}
