package eu.throup.aoc.year2021.day10

import eu.throup.aoc.year2021.day10.CommandChar.{isOpener, partner}
import eu.throup.aoc.year2021.day10.Walker.{
  Complete,
  Corrupt,
  Incomplete,
  OutcomeMapper,
  WalkOutcome
}

case class Walker[A](mapper: OutcomeMapper[A]) {
  def apply(
      remaining: String,
      walked: Seq[CommandChar] = Seq()
  ): A =
    if (remaining.isEmpty) {
      if (walked.isEmpty)
        mapper(Complete, remaining, walked)
      else
        mapper(Incomplete, remaining, walked)
    } else {
      if (isOpener(remaining.head))
        apply(remaining.tail, remaining.head +: walked)
      else if (walked.head == partner(remaining.head))
        apply(remaining.tail, walked.tail)
      else
        mapper(Corrupt, remaining, walked)
    }
}

object Walker {
  type OutcomeMapper[A] = (WalkOutcome, String, Seq[CommandChar]) => A

  sealed trait WalkOutcome
  object Complete extends WalkOutcome
  object Corrupt extends WalkOutcome
  object Incomplete extends WalkOutcome
}
