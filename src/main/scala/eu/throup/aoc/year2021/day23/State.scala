package eu.throup.aoc.year2021.day23

import eu.throup.aoc.year2021.day23.State.RoomState
import eu.throup.aoc.year2021.day23.State.RoomState.*
import eu.throup.aoc.year2021.day23.Tile.{Door, Hallway, Room}

enum Tile { case Hallway, Door, Room}
case class State(
    hallway: Seq[Option[Char]],
    roomA: Seq[Option[Char]],
    roomB: Seq[Option[Char]],
    roomC: Seq[Option[Char]],
    roomD: Seq[Option[Char]]
) {
  def parts = Seq(hallway, roomA, roomB, roomC, roomD)

  def nextStates: Set[State] = {
    val raS: Set[State] = forRoom('A', roomA) match {
      case Messy =>
        val first: Option[Char] = roomA.collectFirst { case Some(a) => a}
        val i: Int = roomA.indexOf(first)
        val newRoomA: Seq[Option[Char]] = (roomA.take(i) :+ None) ++ roomA.drop(i + 1)
        val hh = for {
          i <- hallway.indices
          if (hallway(i) == None)
          newH: Seq[Option[Char]] = (hallway.take(i) :+ first) ++ hallway.drop(i + 1)
        } yield newH
        hh.map(State(_, newRoomA, roomB, roomC, roomD)).toSet

      case Open =>
        Set()

      case Closed =>
        Set()
    }
    val rbS: Set[State] = forRoom('B', roomB) match {
      case Messy =>
        val first: Option[Char] = roomB.collectFirst { case Some(b) => b}
        val i: Int = roomB.indexOf(first)
        val newRoomB: Seq[Option[Char]] = (roomB.take(i) :+ None) ++ roomB.drop(i + 1)
        val hh = for {
          i <- hallway.indices
          if (hallway(i) == None)
          newH: Seq[Option[Char]] = (hallway.take(i) :+ first) ++ hallway.drop(i + 1)
        } yield newH
        hh.map(State(_, roomA, newRoomB, roomC, roomD)).toSet

      case Open =>
        Set()

      case Closed =>
        Set()
    }
    val rcS: Set[State] = forRoom('C', roomC) match {
      case Messy =>
        val first: Option[Char] = roomC.collectFirst { case Some(c) => c}
        val i: Int = roomC.indexOf(first)
        val newRoomC: Seq[Option[Char]] = (roomC.take(i) :+ None) ++ roomC.drop(i + 1)
        val hh = for {
          i <- hallway.indices
          if (hallway(i) == None)
          newH: Seq[Option[Char]] = (hallway.take(i) :+ first) ++ hallway.drop(i + 1)
        } yield newH
        hh.map(State(_, roomA, roomB, newRoomC, roomD)).toSet

      case Open =>
        Set()

      case Closed =>
        Set()
    }
    val rdS: Set[State] = forRoom('D', roomD) match {
      case Messy =>
        val first: Option[Char] = roomD.collectFirst { case Some(d) => d}
        val i: Int = roomD.indexOf(first)
        val newRoomD: Seq[Option[Char]] = (roomD.take(i) :+ None) ++ roomD.drop(i + 1)
        val hh = for {
          i <- hallway.indices
          if (hallway(i) == None)
          newH: Seq[Option[Char]] = (hallway.take(i) :+ first) ++ hallway.drop(i + 1)
        } yield newH
        hh.map(State(_, roomA, roomB, roomC, newRoomD)).toSet

      case Open =>
        Set()

      case Closed =>
        Set()
    }
    raS ++ rbS ++ rcS ++ rdS
  }

  val hallwayPaths: Seq[Seq[Seq[(Tile, Option[Char])]]] = {
    Seq(
      // 0
      Seq(
        Seq(
          Hallway -> hallway(1),
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Hallway -> hallway(1),
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Hallway -> hallway(1),
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Hallway -> hallway(1),
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
      // 1
      Seq(
        Seq(
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
      // 2
      Seq(
        Seq(
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
      // 3
      Seq(
        Seq(
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
      // 4
      Seq(
        Seq(
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
      // 5
      Seq(
        Seq(
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
      // 6
      Seq(
        Seq(
          Hallway -> hallway(5),
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
          Hallway -> hallway(2),
          Door -> None,
        ) ++ roomA.map(Room -> _),
        Seq(
          Hallway -> hallway(5),
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
          Hallway -> hallway(3),
          Door -> None,
        ) ++ roomB.map(Room -> _),
        Seq(
          Hallway -> hallway(5),
          Door -> None,
          Hallway -> hallway(4),
          Door -> None,
        ) ++ roomC.map(Room -> _),
        Seq(
          Hallway -> hallway(5),
          Door -> None,
        ) ++ roomD.map(Room -> _),
      ),
    )
  }

  val roomAPaths: Seq[Seq[(Tile, Option[Char])]] = {
    Seq(
      Seq(
        Door -> None,
        Hallway -> hallway(1),
        Hallway -> hallway(0),
      ),
      Seq(
        Door -> None,
        Hallway -> hallway(2),
        Door -> None,
      ) ++ roomC.map(Room -> _),
      Seq(
        Door -> None,
        Hallway -> hallway(2),
        Door -> None,
        Hallway -> hallway(3),
        Door -> None,
      ) ++ roomC.map(Room -> _),
      Seq(
        Door -> None,
        Hallway -> hallway(2),
        Door -> None,
        Hallway -> hallway(3),
        Door -> None,
        Hallway -> hallway(4),
        Door -> None,
      ) ++ roomD.map(Room -> _),
      Seq(
        Door -> None,
        Hallway -> hallway(2),
        Door -> None,
        Hallway -> hallway(3),
        Door -> None,
        Hallway -> hallway(4),
        Door -> None,
        Hallway -> hallway(5),
        Hallway -> hallway(6),
      ),
    )
  }
}

object State {
  enum RoomState {case Messy, Open, Closed}
  object RoomState {
    def forRoom(room: Char, occupants: Seq[Option[Char]]): RoomState = {
      val o = occupants.collect{case Some(c) => c}
      if (o.find(_ != room).nonEmpty) Messy
      else if (o.length == occupants.length) Closed
      else Open
    }
  }

  def apply(input: String): State = {
    val parsed: Seq[Array[Option[Char]]] = parseInput(input)
    State(
      parsed(0),
      parsed(1),
      parsed(2),
      parsed(3),
      parsed(4)
    )
  }

  def parseInput(input: String): Seq[Array[Option[Char]]] = {
    val lines = input.trim
      .split("\n")

    val h = lines(1)
    val hallwayChars = Array(
      h(1),
      h(2),
      h(4),
      h(6),
      h(8),
      h(10),
      h(11)
    )

    val roomAChars = lines.drop(2).dropRight(1).map(_(3))
    val roomBChars = lines.drop(2).dropRight(1).map(_(5))
    val roomCChars = lines.drop(2).dropRight(1).map(_(7))
    val roomDChars = lines.drop(2).dropRight(1).map(_(9))

    val hallway: Array[Option[Char]] = hallwayChars.map(parseTile)
    val roomA: Array[Option[Char]] = roomAChars.map(parseTile)
    val roomB: Array[Option[Char]] = roomBChars.map(parseTile)
    val roomC: Array[Option[Char]] = roomCChars.map(parseTile)
    val roomD: Array[Option[Char]] = roomDChars.map(parseTile)

    Seq(
      hallway,
      roomA,
      roomB,
      roomC,
      roomD
    )
  }

  def parseTile(input: Char): Option[Char] = input match {
    case '.' => None
    case _   => Some(input)
  }
}
