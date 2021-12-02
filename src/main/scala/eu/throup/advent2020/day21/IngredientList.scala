package eu.throup.advent2020.day21

import scala.language.postfixOps

case class IngredientList(private val input: String) {
  val lines: Seq[Line] = parseLines(input)
  val allIngredients: Set[String] = lines.flatMap(_.ingredients).toSet
  val allAllergens: Set[String] = lines.flatMap(_.allergens).toSet

  var unidentifiedIngredients: Set[String] = allIngredients
  var unidentifiedAllergens: Set[String] = allAllergens
  var processedLines: Seq[Line] = lines
  var identifiedAllergens: Map[String, String] = Map.empty

  while (unidentifiedAllergens.nonEmpty) {
    val fresh: Map[String, String] = identifyAllergens(processedLines, unidentifiedAllergens)
    unidentifiedIngredients --= fresh.values
    unidentifiedAllergens --= fresh.keys
    processedLines = processedLines.map(_.identifyAll(fresh))
    identifiedAllergens ++= fresh
  }

  def countRemaining: Long = {
    val value = processedLines.map(_.ingredients)
    val value1 = value.map(i => i.size)
    val sum = value1.sum
    sum
  }

  private def identifyAllergens(lines: Seq[Line], allAllergens: Set[String]): Map[String, String] = {
    allAllergens.map(a => a -> lines)
      .map({ case (a, l) => a -> l.filter(_.hasAllergen(a)) })
      .map({ case (a, l) => a -> commonIngredients(l) })
      .filter({ case (_, l) => l.size == 1 })
      .map({ case (a, l) => a -> l.head })
      .toMap
  }

  private def commonIngredients(lines: Seq[Line]): Set[String] = lines.map(_.ingredients).reduce((a, b) => a intersect b)

  private def parseLines(input: String) = input.split("\n").map(parseLine)

  private def parseLine(input: String) = {
    val pattern = "^([^\\(]+)\\s+\\(contains\\s+([^\\)]+)\\)$" r
    val found = pattern.findFirstMatchIn(input).get

    Line(
      found.group(1).split("\\s").toSet,
      found.group(2).split(",\\s").toSet
    )
  }
}

case class Line(ingredients: Set[String], allergens: Set[String]) {
  def identifyAll(allergenMap: Map[String, String]): Line = {
    Line(
      ingredients -- allergenMap.values,
      allergens -- allergenMap.keys
    )
  }

  def hasIngredient(ingredient: String): Boolean = {
    ingredients.contains(ingredient)
  }

  def hasAllergen(allergen: String): Boolean = {
    allergens.contains(allergen)
  }
}
