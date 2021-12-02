package eu.throup.advent2020.day20

case class Tile(id: Long, rows: Array[String]) {
  def huntMonsters(monster: Array[String]): Tile = {
    val myHeight = rows.length
    val myWidth = rows.head.length
    val moHeight = monster.length
    val moWidth = monster.head.length

    val newRows: Array[String] = rows.clone()
    for (y <- 0 until myHeight - moHeight) {
      for (x <- 0 until myWidth - moWidth) {
        val found = (0 until moHeight).forall(j => {
          (0 until moWidth).forall(i => {
            (monster(j)(i) != '#') || (newRows(y + j)(x + i) == '#')
          })
        })

        if (found) {
          (0 until moHeight).foreach(j => {
            (0 until moWidth).foreach(i => {
              if (monster(j)(i) == '#') {
                newRows.update(y + j, newRows(y + j).updated(x + i, '.'))
              }
            })
          })
        }
      }
    }

    new Tile(id, newRows)
  }

  val b1: String = rows.head
  val b2: String = rows.map(_.last).mkString
  val b3: String = rows.last.reverse
  val b4: String = rows.reverse.map(_.head).mkString
  val borders: Seq[String] = Seq(b1, b2, b3, b4)

  lazy val islands: Long = {
    rows.map(row => row.count(_ == '#')).sum
  }

  lazy val innerImage: Array[String] = {
    val innerRows: Array[String] = rows.tail.reverse.tail.reverse
    innerRows.map(_.tail.reverse.tail.reverse)
  }

  lazy val flipH: Tile = new Tile(
    id,
    rows.map(r => r.reverse)
  )

  lazy val flipV: Tile = new Tile(
    id,
    rows.reverse
  )

  lazy val turnR: Tile = {
    new Tile(
      id,
      rows.indices.map(i => rows.reverse.map(_ (i)).mkString).toArray
    )
  }

  lazy val turnL: Tile = {
    new Tile(
      id,
      rows.indices.reverse.map(i => rows.map(_ (i)).mkString).toArray
    )
  }
}
object Tile {
  def apply(input: String): Tile = {
    val raw = input.split("\n")
    val id = raw.head.substring(5, 9).toLong
    val rows = raw.tail
    new Tile(id, rows)
  }
}
