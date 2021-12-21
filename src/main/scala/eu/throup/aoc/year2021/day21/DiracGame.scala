package eu.throup.aoc.year2021.day21

import eu.throup.aoc.year2021.day21.DiracGame.{
  ifPlayer1Wins,
  ifPlayer2Wins,
  outcomeFrequencies
}

import scala.annotation.tailrec
import scala.collection.immutable

case class DiracGame(player1Start: Int, player2Start: Int) {
  private lazy val dualStates: Map[DualState, Long] =
    outcomeFrequencies(player1Start, player2Start)

  def player1WinCount = dualStates.filter(ifPlayer1Wins).values.sum
  def player2WinCount = dualStates.filter(ifPlayer2Wins).values.sum
}

object DiracGame {
  def ifPlayer1Wins = (d: (DualState, _)) => { d._1._1._2 > d._1._2._2 }
  def ifPlayer2Wins = (d: (DualState, _)) => { d._1._1._2 < d._1._2._2 }

  def outcomeFrequencies(
      player1Start: Int,
      player2Start: Int
  ): Map[DualState, Long] =
    loopDiracRounds(
      Map(((player1Start, 0), (player2Start, 0), 0) -> 1L)
    )

  @tailrec
  def loopDiracRounds(
      prevStateFreqs: Map[DualState, Long],
      round: Int = 0
  ): Map[DualState, Long] = {
    val stateFreqs = jointDiracRound(prevStateFreqs, round + 1)
    if (stateFreqs == prevStateFreqs) stateFreqs
    else loopDiracRounds(stateFreqs, round + 1)
  }

  def jointDiracRound(
      stateFreqs: Map[DualState, Long],
      round: Int
  ): Map[DualState, Long] =
    manyTurnResults(stateFreqs, round).flatten
      .groupBy(_._1)
      .map { case (key, inner) => key -> inner.map(_._2).sum }

  def manyTurnResults(
      stateFreqs: Map[DualState, Long],
      round: Int
  ): Seq[Map[DualState, Long]] = {
    for {
      stateFreq: (DualState, Long) <- stateFreqs
      dualState: DualState = stateFreq._1
      player1: PlayerState = dualState._1
      player2: PlayerState = dualState._2
      freq: Long = stateFreq._2
    } yield
      if (player1._2 >= 21 || player2._2 >= 21) Map(stateFreq)
      else turnResult(round, player1, player2, freq)
  }.toSeq

  def turnResult(
      round: Int,
      player1: PlayerState,
      player2: PlayerState,
      freq: Long
  ): Map[DualState, Long] =
    if (round % 2 == 0)
      playTurn(player2, freq)
        .map { case (newState, newFreq) =>
          (player1, newState, round + 1) -> newFreq
        }
    else
      playTurn(player1, freq)
        .map { case (newState, newFreq) =>
          (newState, player2, round + 1) -> newFreq
        }

  def playTurn(player: PlayerState, freq: Long): Map[(Int, Int), Long] =
    diracRollDistribution
      .map { case (roll, rollFreq) =>
        (((player._1 + roll) - 1) % 10 + 1) -> rollFreq
      }
      .map { case (pos, f) =>
        ((pos, (player._2 + pos)) -> f * freq)
      }

  /** Relative frequencies for the total of 3 dice rolls.
    */
  def diracRollDistribution: Map[Int, Int] =
    Map(3 -> 1, 4 -> 3, 5 -> 6, 6 -> 7, 7 -> 6, 8 -> 3, 9 -> 1)
}
