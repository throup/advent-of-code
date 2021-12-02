package eu.throup.advent2020

import scala.language.postfixOps

package object day21 {
  def part1(input: String): Long = {
    val list = IngredientList(input)
    list.countRemaining
  }

  def part2(input: String): String = {
    val list = IngredientList(input)
    list.identifiedAllergens
      .toList
      .sortBy(_._1)
      .map(_._2)
      .mkString(",")
  }

  // ---
}
