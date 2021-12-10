package eu.throup.aoc.year2020.day21

import eu.throup.aoc.DayXX

object Day21 extends DayXX {
  override def part1(input: String): Long =
    IngredientList(input).countRemaining

  override def part2(input: String): String =
    IngredientList(input).identifiedAllergens.toList
      .sortBy(_._1)
      .map(_._2)
      .mkString(",")
}
