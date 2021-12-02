package eu.throup.advent2020

import eu.throup.collection.CircularArray

package object day23 {
  def part1(input: String): String = {
    val round0 = input.split("").map(_.toInt)

    val solArray = playGame(round0, 100)

    solArray.mkString
  }

  // ---

  def part2(input: String, roundLimit: Int): Long = {
    val inputRound: Array[Int] = input.split("").map(_.toInt)
    val limit = 1000000 // SHOULD BE 1 000 000
    val round0: Array[Int] = inputRound ++ (inputRound.length + 1 to limit)

    playGame2(round0, roundLimit)
  }

  // ---

  private def playGame(round0: Array[Int], roundLimit: Int): Array[Int] = {
    val mapTracker: MapCupTracker = new MapCupTracker(round0)

    for (i <- 0 until roundLimit) {
      mapTracker.doJuggle()
    }

    mapTracker.finalPositions
  }

  private def playGame2(round0: Array[Int], roundLimit: Int): Long = {
    val mapTracker: MapCupTracker = new MapCupTracker(round0)

    for (i <- 0 until roundLimit) {
      mapTracker.doJuggle()
    }

    val s0: Long = mapTracker.cupAfter(1)
    val s1: Long = mapTracker.cupAfter(s0.toInt)
    s0 * s1
  }

  class MapCupTracker(val seed: Array[Int]) {
    val length: Int = seed.length

    private var cupMap: Map[Int, Int] = {
      seed.zipWithIndex
        .map({case (c, i) => (c, i + 1) })
        .map({case (c, i) => (c, i % length) })
        .map({case (c, i) => (c, seed(i) ) })
        .toMap
    }

    def cupAt(pos: Long): Int = {
      var cup = iAmHere
      for (i <- 0L until pos) {
        cup = cupAfter(cup)
      }
      cup
    }

    def cupAfter(cup: Int): Int = cupMap(cup)

    private var iAmHere: Int = seed.head

    val limit: Int = seed.length

    var currentP = 0

    def meOn1: Array[Int] = {
      var cup = 1
      val collect: Array[Int] = new Array(length)
      for (i <- 0 until length) {
        collect(i) = cup
        cup = cupAfter(cup)
      }
      collect
    }

    def finalPositions: Array[Int] = {
      var cup = 1
      val collect: Array[Int] = new Array(length - 1)
      for (i <- 0 until length - 1) {
        cup = cupAfter(cup)
        collect(i) = cup
      }
      collect
    }

    def cupsAfter(afterThis: Int, num: Int): Seq[Int] = {
      val r0 = cupAfter(afterThis)
      val r1 = cupAfter(r0)
      val r2 = cupAfter(r1)

      Seq(r0, r1, r2)
    }

    def doJuggle(): Unit = {
      val currentC = cupAt(0)

      val gonnaMove: Seq[Int] = cupsAfter(currentC, 3)

      val destinationC: Int = {
        var destCup = currentC - 1
        if (destCup < 1) destCup = limit
        while (gonnaMove.contains(destCup)) {
          destCup -= 1
          if (destCup < 1) destCup = limit
        }
        destCup
      }

      moveSliceAfter(currentC, 3, destinationC)

      iAmHere = cupAfter(iAmHere)
    }


    def moveSliceAfter(moveFromAfter: Int, sliceLength: Int, insertAfter: Int): Unit = {
      val s0 = cupAfter(moveFromAfter)
      val s1 = cupAfter(s0)
      val s2 = cupAfter(s1)
      val afterSlice = cupAfter(s2)
      val shiftRight = cupAfter(insertAfter)

      val delta = Seq(
        moveFromAfter -> afterSlice,
        insertAfter -> s0,
        s2 -> shiftRight
      )

      cupMap ++= delta
    }


  }


  class CupTracker(val seed: Array[Int]) {
//    private var cupsToPos: Map[Int, Int] = seed.zipWithIndex.toMap
//    private var posToCups: Map[Int, Int] = seed.indices.map(i => i -> seed(i)).toMap

    private var circle = new CircularArray(seed)

    val limit: Int = seed.length

    var currentP = 0

    var me = (0 until limit).map(circle(_)).mkString
    def meOn1: Array[Int] = {
      circle.shiftTo(1).toArray
    }

    def doJuggle(): Unit = {
      val currentC = circle(0)

      val toMoveCs: Array[Int] = circle.arraySlice(1, 4)

      val destinationC = {
        var destCup = currentC - 1
        if (destCup < 1) destCup = limit
        while (toMoveCs.contains(destCup)) {
          destCup -= 1
          if (destCup < 1) destCup = limit
        }
        destCup
      }

      val value = circle.moveSliceAfter(1, 4, destinationC)
      val value3 = if (value(0) == currentC) {
        value.shiftLeft(1)
      } else {
        value.shiftRight(2)
      }
      circle = value3
     // me = (0 until limit).map(circle(_)).mkString
    }
  }

  class OldCupTracker(val seed: Array[Int]) {
    private var cupsToPos: Map[Int, Int] = seed.zipWithIndex.toMap
    private var posToCups: Map[Int, Int] = seed.indices.map(i => i -> seed(i)).toMap

    val limit: Int = seed.length

    var currentP = 0

    var me = (0 until limit).map(cupAt).mkString
    def meOn1: Array[Int] = {
      val start: Int = posOf(1)
      (0 until limit).toArray.map(_ + start).map(cupAt)
    }

    def doJuggle() = {
      val currentC: Int = cupAt(currentP)
      val toMoveCs: Array[Int] = Array(cupAt(currentP + 1), cupAt(currentP + 2), cupAt(currentP + 3))
      val startOfRange: Int = (currentP + 1) % limit
      val endOfRange: Int = (currentP + 3) % limit

      val destinationC: Int = {
        var destCup: Int = currentC - 1
        if (destCup < 1) destCup = limit
        while (toMoveCs.contains(destCup)) {
          destCup -= 1
          if (destCup < 1) destCup = limit
        }
        destCup
      }
      val destinationP: Int = posOf(destinationC)

      val move1: Int = destinationP - currentP + (if (destinationP > currentP) 0 else limit)
      val move2: Int = endOfRange - destinationP + (if (destinationP < endOfRange) 0 else limit)

      if (move1 <= move2) {
        val fullPs: Array[Int] = if (destinationP < startOfRange) {
          ((startOfRange until limit) ++ (0 to destinationP)).toArray
        } else {
          (startOfRange to destinationP).toArray
        }
        val otherPs: Array[Int] = if (destinationP < endOfRange) {
          ((endOfRange + 1 until limit) ++ (0 to destinationP)).toArray
        } else {
          (endOfRange + 1 to destinationP).toArray
        }

        val otherCs: Array[Int] = otherPs.map(cupAt)
        val replacement: Array[Int] = otherCs ++ toMoveCs

        posToCups ++= fullPs.zip(replacement).toMap
        cupsToPos ++= replacement.zip(fullPs).toMap
      } else {
        val fullPs: Array[Int] = if (destinationP > endOfRange) {
          ((destinationP + 1 until limit) ++ (0 to endOfRange)).toArray
        } else {
          (destinationP + 1 to endOfRange).toArray
        }
        val otherPs: Array[Int] = if (destinationP > currentP) {
          ((destinationP + 1 until limit) ++ (0 to currentP)).toArray
        } else {
          (destinationP + 1 to currentP).toArray
        }

        val otherCs: Array[Int] = otherPs.map(cupAt)
        val replacement: Array[Int] = toMoveCs ++ otherCs

        posToCups ++= fullPs.zip(replacement).toMap
        cupsToPos ++= replacement.zip(fullPs).toMap
      }

      currentP = (posOf(currentC) + 1) % limit
      me = (0 until limit).map(cupAt).mkString
    }

    def cupAt(pos: Int): Int = posToCups(pos % limit)
    def posOf(cup: Int): Int = cupsToPos(cup)
  }
}
