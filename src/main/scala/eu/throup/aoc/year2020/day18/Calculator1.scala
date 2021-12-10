package eu.throup.aoc.year2020.day18

case class Calculator1(expr: String) extends Calculator {
  override def preresolveExpression(input: Seq[String]): Seq[String] = input
}
