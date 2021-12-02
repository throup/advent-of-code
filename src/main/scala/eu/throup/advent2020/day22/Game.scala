package eu.throup.advent2020.day22

class Game(startPlayer1: Array[Int], startPlayer2: Array[Int], val recursive: Boolean = false) {
  var player1: Array[Int] = startPlayer1
  var player2: Array[Int] = startPlayer2

  def winner(): Int = {
    if (!GameTracker.tracked(startPlayer1, startPlayer2)) {
      GameTracker.track(startPlayer1, startPlayer2, playGame())
    }
    GameTracker.winner(startPlayer1, startPlayer2)
  }

  def score: BigInt = {
    val winningHand = if (winner() == 1) player1 else player2
    scoreFor(winningHand)
  }

  private def scoreFor(player: Array[Int]): BigInt = {
    val scoring: Array[Int] = player.reverse
    scoring.indices
      .map(i => (BigInt(1) + i) * scoring(i))
      .sum
  }

  private def playGame(): Int = {
    var keepPlaying = true
    var tracker = Set(keyForRound(player1, player2))

    var forceWinner = false
    while (keepPlaying) {
      val card1 = player1.head
      val rest1 = player1.tail
      val card2 = player2.head
      val rest2 = player2.tail

      val getRecursive = recursive && (rest1.length >= card1 && rest2.length >= card2)
      val winner = if (getRecursive) {
        subGame(rest1.slice(0, card1), rest2.slice(0, card2))
      } else {
        if (card1 > card2) 1 else 2
      }

      if (winner == 1) {
        player1 = rest1 ++ Seq(card1, card2)
        player2 = rest2
      } else {
        player1 = rest1
        player2 = rest2 ++ Seq(card2, card1)
      }

      val newKey = keyForRound(player1, player2)
      if (tracker.contains(newKey)) {
        // Recursive. Die!
        keepPlaying = false
        forceWinner = true
      } else {
        tracker += newKey
        keepPlaying = player1.nonEmpty && player2.nonEmpty
      }
    }

    if (forceWinner || player1.nonEmpty) 1 else 2
  }

  private def subGame(player1: Array[Int], player2: Array[Int]): Int = {
    if (!GameTracker.tracked(player1, player2)) {
      GameTracker.track(player1, player2, new Game(player1, player2, recursive).winner())
    }
    GameTracker.winner(player1, player2)
  }

  private def keyForRound(player1: Array[Int], player2: Array[Int]) = {
    player1.mkString(",") + ":" ++ player2.mkString(",")
  }
}

object GameTracker {
  private var tracker: Map[String, Int] = Map.empty

  def reset(): Unit = {
    tracker = Map.empty
  }

  def tracked(player1: Array[Int], player2: Array[Int]): Boolean = {
    tracker.contains(keyForSpecificGame(player1, player2))
  }

  private def keyForSpecificGame(player1: Array[Int], player2: Array[Int]): String = {
    player1.mkString(",") + ":" ++ player2.mkString(",")
  }

  def track(player1: Array[Int], player2: Array[Int], winner: Int): Unit = {
    val comb = keyForSpecificGame(player1, player2)
    tracker += comb -> winner
  }

  def winner(player1: Array[Int], player2: Array[Int]): Int = {
    val comb = keyForSpecificGame(player1, player2)
    tracker.getOrElse(comb, 1)
  }
}
