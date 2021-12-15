package eu.throup.aoc.year2021.day15

case class Node(value: Int, bestPath: Option[Int] = None) {
  val offer: Option[Int] = bestPath.map(_ + value)
  def newBest(int: Int): Node = copy(bestPath = Some(int))
}
