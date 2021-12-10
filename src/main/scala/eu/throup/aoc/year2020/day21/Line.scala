package eu.throup.aoc.year2020.day21

case class Line(ingredients: Set[String], allergens: Set[String]) {
  def identifyAll(allergenMap: Map[String, String]): Line =
    Line(
      ingredients -- allergenMap.values,
      allergens -- allergenMap.keys
    )

  def hasIngredient(ingredient: String): Boolean =
    ingredients.contains(ingredient)

  def hasAllergen(allergen: String): Boolean =
    allergens.contains(allergen)
}
