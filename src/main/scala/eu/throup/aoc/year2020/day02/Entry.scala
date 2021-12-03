package eu.throup.aoc.year2020.day02

final case class Entry(policy: PasswordPolicy, password: Password) {
  def test(): Boolean = policy.test(password)
}
