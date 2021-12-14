package eu.throup.aoc.year2021.day14

import eu.throup.aoc.DayXX

import scala.annotation.tailrec
import scala.collection.{MapView, immutable}

object Day14 extends DayXX {
  override def part1(input: String) = solveForSteps(input, 10)
  override def part2(input: String) = solveForSteps(input, 40)

  def solveForSteps(input: String, steps: Int) = {
    val (template: String, rules: RuleSet) = parseInput(input)
    val afterLoop: Template = loopRules(Template(template), rules, steps)

    // Because we are going to count pairs of elements, the first and last entries will be underrepresented in the final
    // tallies. To correct that, we capture both elements here (they may be the same) to use for correction at the end.
    val correctionMap: Map[Element, Long] =
      Map(template.head -> 1L) + Map(template.reverse.head -> 1L)

    val correctedElementCount: Map[Element, Long] =
      elementCount(afterLoop) + correctionMap

    val values = correctedElementCount.values
    (values.max - values.min) / 2
  }

  @tailrec
  def loopRules(template: Template, rules: RuleSet, remaining: Int): Template =
    if (remaining < 1) template
    else loopRules(rules(template), rules, remaining - 1)

  def elementCount(afterLoop: Template): Map[Element, Long] =
    sumSomeMaps(
      afterLoop
        .map { case (p, c) => Map(p._1 -> c) + Map(p._2 -> c) }
        .toSeq *
    )

  def sumSomeMaps[A](maps: Map[A, Long]*): Map[A, Long] = maps.head + maps.tail

  def parseInput(input: String): (String, RuleSet) = {
    val strings = input.trim.split("\n\n")
    (strings(0), RuleSet(strings(1)))
  }
}
