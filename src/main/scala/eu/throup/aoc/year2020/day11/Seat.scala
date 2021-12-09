package eu.throup.aoc.year2020.day11

import scala.util.control.Breaks.{break, breakable}

abstract class Seat {
  def shuffle(adjacent: Set[Seat], tolerence: Int = 4): Seat
  def occupied(): Boolean
  def exists(): Boolean // crazy!
}
/*
If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
Otherwise, the seat's state does not change.
 */
class EmptySeat extends Seat {
  override def shuffle(adjacent: Set[Seat], tolerence: Int = 4): Seat = {
    val count: Int = adjacent.count(_.occupied())
    if (count == 0) {
      new OccupiedSeat
    } else {
      this
    }
  }

  override def occupied(): Boolean = false
  override def exists(): Boolean = true
}
class OccupiedSeat extends Seat {
  override def shuffle(adjacent: Set[Seat], tolerence: Int = 4): Seat = {
    val count: Int = adjacent.count(_.occupied())
    if (count >= tolerence) {
      new EmptySeat
    } else {
      this
    }
  }

  override def occupied(): Boolean = true
  override def exists(): Boolean = true
}
class NullSeat extends Seat {
  override def shuffle(adjacent: Set[Seat], tolerence: Int = 4): Seat = this

  override def occupied(): Boolean = false
  override def exists(): Boolean = false
}
object Seat {
  def apply(in: String): Seat = {
    in match {
      case "." => new NullSeat
      case "L" => new EmptySeat
      case "#" => new OccupiedSeat
    }
  }
}

abstract class SeatingPlan(private val input: String) {
  var rows: Array[Array[Seat]] =
    input
      .split("\n")
      .map(_.split("").map(Seat(_)))

  def seatAt(x: Int, y: Int): Seat = {
    if (rows.indices.contains(x) && rows(0).indices.contains(y)) {
      rows(x)(y)
    } else {
      Seat(".")
    }
  }

  def shuffle(): Boolean

  def shuffleCount(): Long = {
    var counter = 0L
    while (shuffle()) {
      counter += 1L
    }
    counter
  }

  def occupied(): Long = {
    rows.flatten.count(_.occupied())
  }
}

class SeatingPlanV1(str: String) extends SeatingPlan(str) {
  override def shuffle(): Boolean = {
    val newRows: Array[Array[Seat]] = rows.indices
      .map(_ => rows.head.indices.map(_ => Seat(".")).toArray)
      .toArray
    var changed = false
    for (i <- rows.indices) {
      for (j <- rows.head.indices) {
        val mySeat = seatAt(i, j)
        val adjacent: Set[Seat] = Set(
          seatAt(i - 1, j - 1),
          seatAt(i, j - 1),
          seatAt(i + 1, j - 1),
          seatAt(i - 1, j),
          seatAt(i + 1, j),
          seatAt(i - 1, j + 1),
          seatAt(i, j + 1),
          seatAt(i + 1, j + 1)
        )
        val seat = mySeat.shuffle(adjacent, 4)
        changed = changed || (seat != mySeat)
        newRows(i)(j) = seat
      }
    }
    rows = newRows
    changed
  }
}

class SeatingPlanV2(str: String) extends SeatingPlan(str) {
  override def shuffle(): Boolean = {
    val newRows: Array[Array[Seat]] = rows.indices
      .map(_ => rows.head.indices.map(_ => Seat(".")).toArray)
      .toArray
    var changed = false
    for (i <- rows.indices) {
      for (j <- rows.head.indices) {
        val mySeat = seatAt(i, j)
        val adjacent: Set[Seat] = Set(
          seatFrom(i, j, -1, -1),
          seatFrom(i, j, 0, -1),
          seatFrom(i, j, 1, -1),
          seatFrom(i, j, -1, 0),
          seatFrom(i, j, 1, 0),
          seatFrom(i, j, -1, 1),
          seatFrom(i, j, 0, 1),
          seatFrom(i, j, 1, 1)
        )
        val seat = mySeat.shuffle(adjacent, 5)
        changed = changed || (seat != mySeat)
        newRows(i)(j) = seat
      }
    }
    rows = newRows
    changed
  }

  def seatFrom(x: Int, y: Int, dirX: Int, dirY: Int): Seat = {
    var tar: Seat = Seat(".")
    var i = x + dirX
    var j = y + dirY
    breakable(
      while (rows.indices.contains(i) && rows(0).indices.contains(j)) {
        val newS = seatAt(i, j)
        if (newS.exists()) {
          tar = newS
          break
        }
        i += dirX
        j += dirY
      }
    )
    tar
  }
}
