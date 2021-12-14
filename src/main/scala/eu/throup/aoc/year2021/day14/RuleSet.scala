package eu.throup.aoc.year2021.day14

import eu.throup.aoc.year2021.day14.Day14.sumSomeMaps

import scala.collection.immutable

type RuleSet = Map[Pair, Template]
extension(rules: RuleSet) {
  def apply(template: Template): Template = {
    sumSomeMaps(
      template
        .map { case (p, l) => rules(p) * l }
        .toSeq *
    )
  }
}

object RuleSet {
  def apply(input: String): RuleSet =
    input
      .split("\n")
      .map(Rule(_))
      .toMap
}
