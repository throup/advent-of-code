package eu.throup.aoc.year2021.day18

import scala.annotation.tailrec

object Tokeniser {
  @tailrec
  def tokenise(input: String, collected: Seq[Token] = Seq()): Seq[Token] =
    if (input.isEmpty) collected.reverse
    else
      nextToken(input) match {
        case n: Number =>
          tokenise(input.drop(n.value.toString.length), n +: collected)
        case other => tokenise(input.tail, other +: collected)
      }

  def nextToken(input: String): Token =
    input.headOption match {
      case None      => Empty
      case Some('[') => StartPair
      case Some(']') => EndPair
      case Some(',') => Separator
      case Some(n) =>
        val left = n - 48
        nextToken(input.tail) match {
          case Number(right) => Number(left * 10 + right)
          case _             => Number(left)
        }
    }
}
