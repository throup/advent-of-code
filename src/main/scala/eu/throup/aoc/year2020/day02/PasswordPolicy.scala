package eu.throup.aoc.year2020.day02

import scala.util.matching.Regex

trait PasswordPolicy {
  def test(password: Password): Boolean
  def restriction(character: Char, min: Int, max: Int): Unit

  def apply(input: String): Entry = {
    val pattern: Regex = "^(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w+)$".r

    val matches: Regex.Match = pattern
      .findFirstMatchIn(input)
      .get
    val min = matches.group(1).toInt
    val max = matches.group(2).toInt
    val character = matches.group(3).charAt(0)
    val password = matches.group(4)

    restriction(character, min, max)
    new Entry(this, Password(password))
  }
}
