package eu.throup.aoc.year2020.day18

trait Calculator {
  def expr: String
  def preresolveExpression(input: Seq[String]): Seq[String]

  def result: Long =
    flattenExpression(
      expr
        .replaceAll("\\s", "")
        .split("\\b")
        .flatMap(splitter)
    )

  def resolveExpression(input: Seq[String]): Long = {
    val pieces = preresolveExpression(input)
    var sum = pieces(0).toLong
    var i = 1
    while (i < pieces.length) {
      val operator = pieces(i)
      val next = pieces(i + 1).toLong

      operator match {
        case "+" => sum = sum + next
        case "-" => sum = sum - next
        case "*" => sum = sum * next
        case "/" => sum = sum / next
      }

      i += 2
    }

    sum
  }

  def flattenExpression(pieces: Seq[String]): Long = {
    var expressions: Map[Int, Seq[String]] = Map(0 -> Seq())
    var depth = 0
    for (i <- pieces.indices) {
      val current = pieces(i)

      if (current == "(") {
        depth += 1
        expressions += depth -> Seq()
      } else if (current == ")") {
        val re = resolveExpression(expressions(depth))
        depth -= 1
        expressions += depth -> (expressions(depth) ++ Seq(re.toString))
      } else {
        expressions += depth -> (expressions(depth) ++ Seq(current))
      }
    }

    resolveExpression(
      expressions(depth)
    )
  }

  def splitter(input: String): Seq[String] =
    if (isNumber(input)) {
      Seq(input)
    } else {
      input.split("").toSeq
    }

  def isNumber(input: String): Boolean =
    input.forall(_.isDigit)
}
