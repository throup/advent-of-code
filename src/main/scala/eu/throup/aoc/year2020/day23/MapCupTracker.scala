package eu.throup.aoc.year2020.day23

class MapCupTracker(val seed: Array[Int]) {
  val length: Int = seed.length

  private var cupMap: Map[Int, Int] = {
    seed.zipWithIndex
      .map({ case (c, i) => (c, i + 1) })
      .map({ case (c, i) => (c, i % length) })
      .map({ case (c, i) => (c, seed(i)) })
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

  def moveSliceAfter(
      moveFromAfter: Int,
      sliceLength: Int,
      insertAfter: Int
  ): Unit = {
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
