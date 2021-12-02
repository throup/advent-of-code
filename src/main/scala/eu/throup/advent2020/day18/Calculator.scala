package eu.throup.advent2020.day18

case class Calculator(expr: String) {
  def result: Long = {
    val trimmed = expr.replaceAll("\\s", "")
    val pieces = trimmed.split("\\b").flatMap(splitter)

    val moo = flattenExpression(pieces)



    moo
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

    val value = expressions(depth)
    resolveExpression(value)
  }

  def resolveExpression(pieces: Seq[String]): Long = {
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

  private def splitter(input: String) = {
    if (isNumber(input)) {
      Seq(input)
    } else {
      input.split("").toSeq
    }
  }

  private def isNumber(input: String): Boolean = {
    input.forall(_.isDigit)
  }
}


case class Calculator2(expr: String) {
  def result: Long = {
    val trimmed = expr.replaceAll("\\s", "")
    val pieces = trimmed.split("\\b").flatMap(splitter)

    val moo = flattenExpression(pieces)



    moo
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

    val value = expressions(depth)
    resolveExpression(value)
  }

  def preresolveExpression(pieces: Seq[String]): Seq[String] = {
    var resolved: Seq[String] = pieces
    var i = 1
    while (i < resolved.length) {
      val prev = resolved(i - 1).toLong
      val operator = resolved(i)
      val next = resolved(i + 1).toLong

      operator match {
        case "+" =>
          resolved = resolved.slice(0, i - 1) ++ Seq((prev + next).toString) ++ resolved.slice(i + 2, resolved.length)
        case "-" =>
          resolved = resolved.slice(0, i - 1) ++ Seq((prev - next).toString) ++ resolved.slice(i + 2, resolved.length)
        case _ => i += 2
      }
    }

    resolved
  }

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

  private def splitter(input: String) = {
    if (isNumber(input)) {
      Seq(input)
    } else {
      input.split("").toSeq
    }
  }

  private def isNumber(input: String): Boolean = {
    input.forall(_.isDigit)
  }
}
