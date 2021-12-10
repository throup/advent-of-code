package eu.throup.aoc.year2021.day10

type CommandChar = Char
object CommandChar {
  def isOpener: Map[CommandChar, Boolean] = Map(
    '(' -> true,
    '[' -> true,
    '{' -> true,
    '<' -> true,
    ')' -> false,
    ']' -> false,
    '}' -> false,
    '>' -> false
  )

  def partner: Map[CommandChar, CommandChar] = Map(
    '(' -> ')',
    ')' -> '(',
    '[' -> ']',
    ']' -> '[',
    '{' -> '}',
    '}' -> '{',
    '<' -> '>',
    '>' -> '<'
  )
}
