package eu.throup.aoc.year2021.day21

import eu.throup.aoc.DayXX

import scala.collection.immutable

object Day21 extends DayXX {
  override def part1(input: String) = {
    val (player1, player2) = parseInput(input)
    val game = DeterministicGame(player1, player2)
    game.loserScore * game.rolls
  }

  override def part2(input: String) = {
    val (player1, player2) = parseInput(input)
    val game = DiracGame(player1, player2)
    game.player1WinCount max game.player2WinCount
  }

  def parseInput(input: String): (Int, Int) = {
    val parsed = input.trim
      .split("\n")
      .map(_.split("\\s").reverse.head.toInt)

    (parsed(0), parsed(1))
  }
}
