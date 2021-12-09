package eu.throup.aoc.year2020.day07

import eu.throup.aoc.DayXX

import scala.util.matching.Regex

object Day07 extends DayXX {
  private val SearchKey = "shiny gold"

  override def part1(input: String): Int = {
    val containers = input
      .split("\n")
      .map(new Rule(_))
      .flatMap(r => r.contentTypes.map((_, r.identifier)))
      .groupBy(_._1)
      .mapValues(a => a.map(_._2))
      .toMap

    def deepCollect(input: Set[String]): Set[String] = {
      input.flatMap(str => {
        if (containers.contains(str)) {
          Set(str) ++ deepCollect(containers(str).toSet)
        } else {
          Set(str)
        }
      })
    }

    deepCollect(Set(SearchKey)).size - 1
  }

  override def part2(input: String): Int = {
    val rules = input
      .split("\n")
      .map(new Rule(_))
      .map(r => (r.identifier, r.contentPairs))
      .toMap

    def deepCount(input: String): Int = {
      if (rules.contains(input)) {
        val ints = rules(input)
          .map(r => {
            val value = r._1
            val i = deepCount(r._2)
            value * i
          })
        1 + ints.sum
      } else {
        1
      }
    }

    deepCount(SearchKey) - 1
  }

  def matchGroups(pattern: Regex, input: String): List[String] = {
    val maybeMatch = pattern.findFirstMatchIn(input)
    if (maybeMatch.nonEmpty) {
      maybeMatch.get.subgroups
    } else {
      List.empty
    }
  }
}
