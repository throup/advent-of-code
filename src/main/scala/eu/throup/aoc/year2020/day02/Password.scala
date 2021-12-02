package eu.throup.aoc.year2020.day02

import scala.collection.mutable

class Password(val password: String) {
  def charAt(i: Int): Char = {
    password.charAt(i - 1)
  }

  def count(char: Char): Int = {
    password.count(_ == char)
  }
}
