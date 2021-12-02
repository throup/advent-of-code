package eu.throup.advent2020.day3

class SkiSlope(private val input: String) {
  private val lines: Array[String] = input.split("\n")
  val width: Int = lines(0).length
  val height: Int = lines.length

  def coord(x: Int, y: Int): Char = {
    val line = lines(y % height)
    line.charAt(x % width)
  }

  def trees(x: Int, y: Int): Int = {
    (0 until (height / y))
      .map(i => coord(i * x, i * y))
      .count(c => c == '#')
  }
}
