package eu.throup.aoc.year2020.day02

final case class Password(password: String) extends AnyVal {
  def charAt(i: Int): Char = password.charAt(i - 1)
  def count(char: Char): Int = password.count(_ == char)
}
