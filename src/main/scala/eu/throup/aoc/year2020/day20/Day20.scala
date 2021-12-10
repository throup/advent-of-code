package eu.throup.aoc.year2020.day20

import eu.throup.aoc.DayXX

//TODO: Maybe a _little_ refactoring here...
object Day20 extends DayXX {
  override def part1(input: String): Long = {
    val tiles = parseTiles(input)
    val arranged = arrangeTiles(tiles)

    Seq(
      arranged.head.head,
      arranged.head.last,
      arranged.last.head,
      arranged.last.last
    )
      .map(_.id)
      .product
  }

  def parseTiles(input: String) =
    input
      .split("\n\n")
      .map(Tile(_))

  private def countMatches(tile: Tile, others: Seq[Tile]): Int = {
    val comp: Seq[Tile] = others.filter(t => t.id != tile.id)
    val borders: Seq[String] = tile.borders
    val matches: Seq[Int] = borders.map(b => numMatches(b, comp))
    matches.sum
  }

  private def numMatches(border: String, tiles: Seq[Tile]): Int = {
    val borders = tiles.flatMap(_.borders)
    val reversed = borders.map(_.reverse)

    val combined = borders ++ reversed
    combined.count(_.equals(border))
  }

  override def part2(input: String): Long = {
    val tiles = parseTiles(input)
    val picture = mergeTiles(tiles)

    val hunted1: Tile = picture.huntMonsters(monster)
    val hunted2: Tile = hunted1.turnL.huntMonsters(monster)
    val hunted3: Tile = hunted2.turnL.huntMonsters(monster)
    val hunted4: Tile = hunted3.turnL.huntMonsters(monster)
    val hunted5: Tile = hunted4.flipH.huntMonsters(monster)
    val hunted6: Tile = hunted5.turnL.huntMonsters(monster)
    val hunted7: Tile = hunted6.turnL.huntMonsters(monster)
    val hunted8: Tile = hunted7.turnL.huntMonsters(monster)

    hunted8.islands
  }

  val monster: Array[String] =
    "                  # \n#    ##    ##    ###\n #  #  #  #  #  #   ".split(
      "\n"
    )

  def mergeTiles(tiles: Seq[Tile]): Tile = {
    new Tile(0, generateImage(tiles).toArray)
  }

  def generateImageString(tiles: Seq[Tile]): String = {
    generateImage(tiles).mkString("\n")
  }

  private def generateImage(tiles: Seq[Tile]): Seq[String] = {
    arrangeTiles(tiles)
      .map(row => row.map(_.innerImage))
      .flatMap(row => row.head.indices.map(i => row.map(_(i)).mkString))
  }

  private def arrangeTiles(tiles: Seq[Tile]): Seq[Seq[Tile]] = {
    val corners = tiles.filter(t => countMatches(t, tiles) == 2)
    val firstCorner = corners.head
    val otherTiles = tiles.filter(_.id != firstCorner.id)
    val startingPosition = startingOrientation(firstCorner, otherTiles)

    arrangeTiles(startingPosition, otherTiles)
  }

  private def arrangeTiles(
      startingPosition: Tile,
      otherTiles: Seq[Tile]
  ): Seq[Seq[Tile]] = {
    val imageSize: Int = math.sqrt(otherTiles.length + 1).toInt

    var initialTile = startingPosition
    var setOfOtherTiles: Seq[Tile] = otherTiles

    var rows: Seq[Seq[Tile]] = Seq.empty

    for (dir <- Right to Up) {
      rows ++= Seq(findRowOfTiles(initialTile, setOfOtherTiles, imageSize, dir))
      setOfOtherTiles =
        setOfOtherTiles.filter(t => !rows.last.map(_.id).contains(t.id))
      initialTile = rows.last.last
    }

    val innerImage = if (setOfOtherTiles.nonEmpty) {
      val firstInnerTile =
        tileMatchingBorder(rows(0)(1).b3, setOfOtherTiles).get.flipH
      setOfOtherTiles = setOfOtherTiles.filter(t => firstInnerTile.id != t.id)

      if (setOfOtherTiles.length > 1) {
        arrangeTiles(firstInnerTile, setOfOtherTiles)
      } else {
        Seq(Seq(firstInnerTile))
      }
    } else Seq.empty

    val midLeft = rows(3).reverse.slice(0, imageSize - 2)
    val midRight = rows(1).slice(1, imageSize - 1)

    val midImage: Seq[Seq[Tile]] = innerImage.indices
      .map(i => Seq(midLeft(i)) ++ innerImage(i) ++ Seq(midRight(i)))

    Seq(rows(0)) ++ midImage ++ Seq(rows(2).reverse)
  }

  private def findRowOfTiles(
      initialTile: Tile,
      otherTiles: Seq[Tile],
      n: Int,
      dir: Int
  ): Seq[Tile] = {
    val optNextTile = dir match {
      case Up => tileMatchingBorder(initialTile.b1, otherTiles).map(_.flipV)
      case Right =>
        tileMatchingBorder(initialTile.b2, otherTiles).map(_.flipH).map(_.turnL)
      case Down => tileMatchingBorder(initialTile.b3, otherTiles).map(_.flipH)
      case Left =>
        tileMatchingBorder(initialTile.b4, otherTiles).map(_.flipH).map(_.turnR)
    }
    if (optNextTile.nonEmpty) {
      val nextTile = optNextTile.get
      val nextOtherTiles = otherTiles.filter(_.id != nextTile.id)
      Seq(initialTile) ++ findRowOfTiles(nextTile, nextOtherTiles, n - 1, dir)
    } else {
      Seq(initialTile)
    }
  }

  def startingOrientation(tile: Tile, others: Seq[Tile]): Tile = {
    val t1 = tileMatchingBorder(tile.b1, others).map(_ => 1).getOrElse(0)
    val t2 = tileMatchingBorder(tile.b2, others).map(_ => 2).getOrElse(0)
    val t3 = tileMatchingBorder(tile.b3, others).map(_ => 4).getOrElse(0)
    val t4 = tileMatchingBorder(tile.b4, others).map(_ => 8).getOrElse(0)

    val sum = t1 + t2 + t3 + t4

    sum match {
      case 3  => tile.flipV // b1 + b2
      case 6  => tile // b2 + b3
      case 9  => tile.flipV.flipH // b1 + b4
      case 12 => tile.flipH // b3 + b4
      case _  => tile
    }
  }

  def tileMatchingBorder(border: String, tiles: Seq[Tile]): Option[Tile] = {
    findMatchingBorder(border, tiles)
      .map(orientToBorder(border, _))
  }

  private def findMatchingBorder(
      border: String,
      tiles: Seq[Tile]
  ): Option[Tile] = {
    val straight = tiles.filter(t => t.borders.contains(border))
    val flipped = tiles.filter(t => t.borders.contains(border.reverse))

    (straight ++ flipped).headOption
  }

  def orientToBorder(border: String, tile: Tile): Tile = border match {
    case tile.b1 => tile
    case tile.b2 => tile.turnL
    case tile.b3 => tile.flipV.flipH
    case tile.b4 => tile.turnR
    case _       => orientToBorder(border.reverse, tile).flipH
  }

  private val Up = 4
  private val Right = 1
  private val Down = 2
  private val Left = 3
}
