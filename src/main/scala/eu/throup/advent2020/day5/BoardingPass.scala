package eu.throup.advent2020.day5

class BoardingPass(private val input: String) {
  val row: Int = {
    binaryScore(
      input.substring(0, 7).reverse
    )
  }

  val col: Int = {
    binaryScore(
      input.substring(7).reverse
    )
  }

  private def binaryScore(reverse: String) = {
    (0 until reverse.length)
      .map(i => math.pow(2, i).toInt -> reverse(i))
      .toMap
      .mapValues(c => if (c == 'R' || c == 'B') 1 else 0)
      .map(e => e._1 * e._2)
      .sum
  }

  val id: Int = {
    row * 8 + col
  }
}
