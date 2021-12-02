package eu.throup.advent2020

import scala.language.postfixOps
import scala.util.matching.Regex

package object day19 {
  def part1(input: String): Long = {
    new RuleEngine(input).solve
  }

  def part2(input: String): Long = {
    new RuleEngine(input).solve
  }
}
