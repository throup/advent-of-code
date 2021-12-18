package eu.throup.aoc.year2021.day18

sealed trait Token
object Empty extends Token
object StartPair extends Token
object EndPair extends Token
object Separator extends Token
case class Number(value: Int) extends Token
