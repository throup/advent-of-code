package eu.throup.aoc.year2020.day02

abstract class PasswordPolicy {
  def test(password: Password): Boolean
}
