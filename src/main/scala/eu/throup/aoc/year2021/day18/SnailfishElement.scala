package eu.throup.aoc.year2021.day18

import eu.throup.aoc.year2021.day18.SnailfishElement.ExplosionResult

import scala.annotation.tailrec

trait SnailfishElement {
  def magnitude: Long
  def split(): SnailfishElement
  def singleExplosion(level: Int = 4): ExplosionResult
  def donateLeft(donation: SnailfishNumber): SnailfishElement
  def donateRight(donation: SnailfishNumber): SnailfishElement

  def explodeAndSplit(
      start: SnailfishElement = this,
      level: Int = 4
  ): SnailfishElement = {
    val first = loopExplosions(start, level).split()
    if (first == start) start
    else explodeAndSplit(first, level)
  }

  def loopExplosions(start: SnailfishElement, level: Int): SnailfishElement = {
    val first = start.singleExplosion(level).map(_._1).getOrElse(start)
    if (first == start) start
    else loopExplosions(first, level)
  }

  def +(other: SnailfishElement) = {
    SnailfishPair(this, other).explodeAndSplit()
  }
}

object SnailfishElement {
  type ExplosionResult =
    Option[(SnailfishElement, Option[SnailfishNumber], Option[SnailfishNumber])]

  def apply(input: String): SnailfishElement =
    parseTokens(
      Tokeniser.tokenise(input, Seq())
    )

  def addAll(
      input: Seq[SnailfishElement],
      sum: Option[SnailfishElement] = None
  ): SnailfishElement = {
    if (input.isEmpty) sum.get
    else
      sum match {
        case Some(running) =>
          addAll(input.tail, Some((running + input.head)))
        case None => addAll(input.tail, Some(input.head))
      }
  }

  def parseTokens(
      toParse: Seq[Token]
  ): SnailfishPair = {
    val body = toParse.tail.dropRight(1)

    val (first, theRest) = parsePart(body)
    val (second, _) = parsePart(theRest.tail)

    SnailfishPair(first, second)
  }

  def parsePart(body: Seq[Token]): (SnailfishElement, Seq[Token]) =
    body.head match {
      case StartPair =>
        val innerSeq = rollUntilEnd(body.tail)
        val pair: SnailfishPair = parseTokens(
          Seq(StartPair) ++: innerSeq ++: Seq(EndPair)
        )
        (pair, body.drop(innerSeq.length + 2))

      case Number(value) => (SnailfishNumber(value), body.tail)
    }

  @tailrec
  def rollUntilEnd(
      unseen: Seq[Token],
      startCount: Int = 0,
      seen: Seq[Token] = Seq()
  ): Seq[Token] = {
    if (unseen.head == EndPair && startCount == 0) seen
    else if (unseen.head == EndPair)
      rollUntilEnd(unseen.tail, startCount - 1, seen :+ unseen.head)
    else if (unseen.head == StartPair)
      rollUntilEnd(unseen.tail, startCount + 1, seen :+ unseen.head)
    else rollUntilEnd(unseen.tail, startCount, seen :+ unseen.head)
  }
}
