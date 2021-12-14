package eu.throup.aoc.year2021.day14

type Rule = (Pair, Template)
object Rule {
  def apply(input: String): Rule = {
    val parts = input.split(" -> ")
    val from = parts(0).toCharArray.toSeq

    val a = from(0)
    val b = parts(1).toCharArray.head
    val c = from(1)

    (a, c) -> Template(
      Seq((a, b), (b, c))
    )
  }
}
