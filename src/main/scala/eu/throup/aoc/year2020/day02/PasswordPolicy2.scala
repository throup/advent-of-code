package eu.throup.aoc.year2020.day02

import scala.collection.mutable

class PasswordPolicy2 extends PasswordPolicy {
  val restrictions: mutable.Map[Char, Seq[Int]] =
    scala.collection.mutable.Map[Char, Seq[Int]]()

  def restriction(character: Char, pos1: Int, pos2: Int): Unit = {
    restrictions(character) = Seq(pos1, pos2)
  }
  def test(password: Password): Boolean = {
    restrictions.forall((e) => {
      val char: Char = e._1
      val range: Seq[Int] = e._2

      val first: Char = password.charAt(range(0))
      val second: Char = password.charAt(range(1))

      ((first == char) && (second != char)) || ((first != char) && (second == char))
    })
  }
}
