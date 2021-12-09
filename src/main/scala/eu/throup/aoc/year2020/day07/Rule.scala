package eu.throup.aoc.year2020.day07

import eu.throup.aoc.year2020.day07.Day07.matchGroups

import scala.language.postfixOps
import scala.util.matching.Regex

class Rule(private val input: String) {
  val matches: List[String] =
    matchGroups("^(.*) bags contain (.*)\\.\\s*$" r, input)
  val identifier: String = matches(0)
  val contents: Array[String] = matches(1).split(",\\s*")

  val contentTypes: Array[String] =
    contents.flatMap(matchGroups("^(?:\\d+) (.+) bags?$" r, _))
  val contentQuantities: Array[Int] =
    contents.flatMap(matchGroups("^(\\d+) (?:.+) bags?$" r, _)).map(_.toInt)
  val contentPairs: Array[(Int, String)] = contentQuantities zip contentTypes
}
