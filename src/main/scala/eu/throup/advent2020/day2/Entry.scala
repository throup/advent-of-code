package eu.throup.advent2020.day2

import scala.collection.mutable

class Entry(val policy: PasswordPolicy, val password: Password) {
  def test(): Boolean = {
    policy.test(password)
  }
}

abstract class PasswordPolicy {
  def test(password: Password): Boolean
}

class PasswordPolicy1 extends PasswordPolicy {
  val restrictions: mutable.Map[Char, Seq[Int]] = scala.collection.mutable.Map[Char, Seq[Int]]()

  def restriction(character: Char, min: Int, max: Int): Unit = {
    restrictions(character) = min to max
  }
  def test(password: Password): Boolean = {
    restrictions.forall(
      (e) => {
        val char: Char = e._1
        val range: Seq[Int] = e._2

        val d: Int = password.count(char)
        range.contains(d)
      }
    )
  }
}

class PasswordPolicy2 extends PasswordPolicy {
  val restrictions: mutable.Map[Char, Seq[Int]] = scala.collection.mutable.Map[Char, Seq[Int]]()

  def restriction(character: Char, pos1: Int, pos2: Int): Unit = {
    restrictions(character) = Seq(pos1, pos2)
  }
  def test(password: Password): Boolean = {
    restrictions.forall(
      (e) => {
        val char: Char = e._1
        val range: Seq[Int] = e._2

        val first: Char = password.charAt(range(0))
        val second: Char = password.charAt(range(1))

        ((first == char) && (second != char)) || ((first != char) && (second == char))
      }
    )
  }
}

class Password(val password: String) {
  def charAt(i: Int): Char = {
    password.charAt(i - 1)
  }

  def count(char: Char): Int = {
    password.count(_ == char)
  }

}
