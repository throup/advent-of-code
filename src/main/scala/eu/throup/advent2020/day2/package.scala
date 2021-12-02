package eu.throup.advent2020

import com.typesafe.scalalogging.Logger

import scala.util.control.Breaks.{break, breakable}
import scala.util.matching.Regex

package object day2 {
  private val Log = Logger(this.getClass)

  def part1(input: Seq[String]): Int = {
    input.map(f => entryParser(f))
      .count(e => e.test())
  }

  def part2(input: Seq[String]): Int = {
    input.map(f => entry2Parser(f))
      .count(e => e.test())
  }

  def entryParser(input: String): Entry = {
    val pattern: Regex = "^(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w+)$".r

    val optionalMatches: Option[Regex.Match] = pattern.findFirstMatchIn(input)
    val matches: Regex.Match = optionalMatches.get
    val min = matches.group(1).toInt
    val max = matches.group(2).toInt
    val character = matches.group(3).charAt(0)
    val password = matches.group(4)

    val policy = new PasswordPolicy1
    policy.restriction(character, min, max)
    new Entry(policy, new Password(password))
  }

  def entry2Parser(input: String): Entry = {
    val pattern: Regex = "^(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w+)$".r

    val optionalMatches: Option[Regex.Match] = pattern.findFirstMatchIn(input)
    val matches: Regex.Match = optionalMatches.get
    val min = matches.group(1).toInt
    val max = matches.group(2).toInt
    val character = matches.group(3).charAt(0)
    val password = matches.group(4)

    val policy = new PasswordPolicy2
    policy.restriction(character, min, max)
    new Entry(policy, new Password(password))
  }
}
