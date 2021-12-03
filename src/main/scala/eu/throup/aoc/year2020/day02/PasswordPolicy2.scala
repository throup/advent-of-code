package eu.throup.aoc.year2020.day02

import scala.collection.mutable

class PasswordPolicy2 extends PasswordPolicy {
  val restrictions: mutable.Map[Char, (Int, Int)] =
    scala.collection.mutable.Map[Char, (Int, Int)]()

  override def restriction(character: Char, pos1: Int, pos2: Int): Unit = {
    restrictions(character) = (pos1, pos2)
  }
  def test(password: Password): Boolean = {
    restrictions.forall {
      case (char: Char, range: (Int, Int)) => {
        val first: Char = password.charAt(range._1)
        val second: Char = password.charAt(range._2)

        ((first == char) && (second != char)) || ((first != char) && (second == char))
      }
    }
  }
}

object PasswordPolicy2 {
  def instance(): PasswordPolicy2 = new PasswordPolicy2
}
