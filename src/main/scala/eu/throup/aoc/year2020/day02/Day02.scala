package eu.throup.aoc.year2020.day02

import com.typesafe.scalalogging.Logger
import eu.throup.aoc.DayXX

import scala.util.control.Breaks.{break, breakable}
import scala.util.matching.Regex

object Day02 extends DayXX {
  private val Log = Logger(this.getClass)

  override def part1(input: String): Int = {
    parseInput(input)
      .map(f => entryParser(f))
      .count(e => e.test())
  }

  override def part2(input: String): Int = {
    parseInput(input)
      .map(f => entry2Parser(f))
      .count(e => e.test())
  }

  def parseInput(input: String): Seq[String] =
    input.trim
      .split("\n")

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
