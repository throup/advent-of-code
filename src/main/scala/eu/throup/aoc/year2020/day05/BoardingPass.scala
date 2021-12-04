package eu.throup.aoc.year2020.day05

case class BoardingPass(input: String) {
  val row: Int =
    binaryScore(
      input.substring(0, 7).reverse
    )

  val col: Int =
    binaryScore(
      input.substring(7).reverse
    )

  def binaryScore(reverse: String) =
    (0 until reverse.length)
      .map(i => math.pow(2, i).toInt -> reverse(i))
      .toMap
      .mapValues(c => if (c == 'R' || c == 'B') 1 else 0)
      .map { _ * _ }
      .sum

  val id: Int =
    row * 8 + col
}
