package eu.throup.aoc.year2020.day02

import scala.collection.mutable

class PasswordPolicy1 extends PasswordPolicy {
  val restrictions: mutable.Map[Char, Seq[Int]] =
    scala.collection.mutable.Map[Char, Seq[Int]]()

  def restriction(character: Char, min: Int, max: Int): Unit = {
    restrictions(character) = min to max
  }
  def test(password: Password): Boolean = {
    restrictions.forall((e) => {
      val char: Char = e._1
      val range: Seq[Int] = e._2

      val d: Int = password.count(char)
      range.contains(d)
    })
  }
}
