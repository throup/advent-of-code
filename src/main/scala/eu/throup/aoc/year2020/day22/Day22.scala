package eu.throup.aoc.year2020.day22

import eu.throup.aoc.DayXX

object Day22 extends DayXX {
  override def part1(input: String): BigInt = {
    GameTracker.reset()
    val hands = parseInput(input)

    val player1: Array[Int] = hands(0)
    val player2: Array[Int] = hands(1)

    val game = new Game(player1, player2, false)
    game.score
  }

  private def parseInput(input: String): Array[Array[Int]] = {
    input
      .split("\n\n")
      .map(_.split("\n"))
      .map(_.tail)
      .map(_.map(_.toInt))
  }

  override def part2(input: String): BigInt = {
    GameTracker.reset()
    val hands = parseInput(input)

    val player1: Array[Int] = hands(0)
    val player2: Array[Int] = hands(1)

    val game = new Game(player1, player2, true)
    game.score
  }
}
