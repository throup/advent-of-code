package eu.throup.aoc.year2020.day02

class Entry(val policy: PasswordPolicy, val password: Password) {
  def test(): Boolean = {
    policy.test(password)
  }
}
