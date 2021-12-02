package eu.throup.advent2020

package object day22 {
  def part1(input: String): BigInt = {
    GameTracker.reset()
    val hands = parseInput(input)

    val player1: Array[Int] = hands(0)
    val player2: Array[Int] = hands(1)

    val game = new Game(player1, player2, false)
    game.score
  }

  // ---

  private def parseInput(input: String): Array[Array[Int]] = {
    input.split("\n\n")
      .map(_.split("\n"))
      .map(_.tail)
      .map(_.map(_.toInt))
  }

  // ---

  def part2(input: String): BigInt = {
    GameTracker.reset()
    val hands = parseInput(input)

    val player1: Array[Int] = hands(0)
    val player2: Array[Int] = hands(1)

    val game = new Game(player1, player2, true)
    game.score
  }
}
