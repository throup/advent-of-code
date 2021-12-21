package eu.throup.aoc.year2021.day21

import eu.throup.aoc.year2021.day21.DeterministicGame.{
  RollsPerTurn,
  deterministicDie,
  playUntil
}

import scala.annotation.tailrec

case class DeterministicGame(player1Start: Int, player2Start: Int) {
  def rolls = RollsPerTurn * turns
  def scores = Set(gameResult._1._2, gameResult._2._2)
  def turns = gameResult._3

  def winnerScore = scores.max
  def loserScore = scores.min

  private lazy val gameResult: (PlayerState, PlayerState, Int) =
    playUntil(1000, deterministicDie(), (player1Start, 0), (player2Start, 0))
}

object DeterministicGame {
  val RollsPerTurn: Int = 3

  @tailrec
  def playUntil(
      target: Int,
      die: Seq[Int],
      player1: PlayerState,
      player2: PlayerState,
      rounds: Int = 0
  ): (PlayerState, PlayerState, Int) = {
    if (player1._2 >= target || player2._2 >= target)
      (player1, player2, rounds)
    else {
      val (newDie, newPlayer1, newPlayer2) =
        takeTurnForPlayer(rounds, die, player1, player2)
      playUntil(target, newDie, newPlayer1, newPlayer2, rounds + 1)
    }
  }

  def takeTurnForPlayer(
      n: Int,
      die: Seq[Int],
      player1: PlayerState,
      player2: PlayerState
  ): (Seq[Int], PlayerState, PlayerState) =
    if (n % 2 == 0)
      (die.drop(RollsPerTurn), rollForPlayer(player1, die), player2)
    else
      (die.drop(RollsPerTurn), player1, rollForPlayer(player2, die))

  def rollForPlayer(player: PlayerState, die: Seq[Int]): PlayerState = {
    val roll: Int = die.take(RollsPerTurn).sum
    (
      (player._1 + roll - 1) % 10 + 1,
      player._2 + ((player._1 + roll - 1) % 10 + 1)
    )
  }

  def deterministicDie(): LazyList[Int] =
    LazyList.from(1).flatMap(_ => (1 to 100))

  def parseInput(input: String) =
    input.trim
      .split("\n")
      .map(_.split("\\s").reverse.head.toInt)
}
