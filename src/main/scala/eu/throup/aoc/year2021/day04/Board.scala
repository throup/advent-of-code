package eu.throup.aoc.year2021.day04

class Board(val rows: Array[Array[Int]]) {
  lazy val cols = rows.transpose

  def howSoon(called: Seq[Int]): Int =
    called.length -
      called.indices
        .map(called.take(_))
        .filter(haveWeWon(_))
        .length

  def haveWeWon(called: Seq[Int]): Boolean =
    (rows ++ cols)
      .exists(_.forall(called.contains(_)))

  def unmarked(called: Seq[Int]): Seq[Int] =
    rows.flatMap(_.diff(called))

  def score(called: Seq[Int]): Long = {
    val soonest = howSoon(called)
    val used = called.take(soonest)
    used(soonest - 1) * unmarked(used).sum
  }
}

object Board {
  def apply(rows: Array[Array[Int]]): Board = new Board(rows)
  def apply(input: Array[String]): Board =
    Board(
      input
        .map(_.trim)
        .map(_.split("\\s+"))
        .map(_.map(_.toInt))
    )
}
