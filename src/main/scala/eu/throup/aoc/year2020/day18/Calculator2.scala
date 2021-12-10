package eu.throup.aoc.year2020.day18

case class Calculator2(expr: String) extends Calculator {
  override def preresolveExpression(pieces: Seq[String]): Seq[String] = {
    var resolved: Seq[String] = pieces
    var i = 1
    while (i < resolved.length) {
      val prev = resolved(i - 1).toLong
      val operator = resolved(i)
      val next = resolved(i + 1).toLong

      operator match {
        case "+" =>
          resolved = resolved.slice(0, i - 1) ++ Seq(
            (prev + next).toString
          ) ++ resolved.slice(i + 2, resolved.length)
        case "-" =>
          resolved = resolved.slice(0, i - 1) ++ Seq(
            (prev - next).toString
          ) ++ resolved.slice(i + 2, resolved.length)
        case _ => i += 2
      }
    }

    resolved
  }
}
