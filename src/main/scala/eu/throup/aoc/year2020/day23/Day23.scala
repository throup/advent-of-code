package eu.throup.aoc.year2020.day23

import eu.throup.aoc.DayXX
import eu.throup.collection.CircularArray

object Day23 extends DayXX {
  override def part1(input: String): Long = {
    val round0 = input.split("").map(_.toInt)

    val solArray = playGame(round0, 100)

    solArray.mkString.toLong
  }

  override def part2(input: String): Long =
    part2(input, 10_000_000)

  def part2(input: String, roundLimit: Int): Long = {
    val inputRound: Array[Int] = input.split("").map(_.toInt)
    val limit = 1000000 // SHOULD BE 1 000 000
    val round0: Array[Int] = inputRound ++ (inputRound.length + 1 to limit)

    playGame2(round0, roundLimit)
  }

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
}
