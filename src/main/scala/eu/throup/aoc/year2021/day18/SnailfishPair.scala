package eu.throup.aoc.year2021.day18

import eu.throup.aoc.year2021.day18.SnailfishElement.ExplosionResult

case class SnailfishPair(left: SnailfishElement, right: SnailfishElement)
    extends SnailfishElement {
  def singleExplosion(level: Int = 4): ExplosionResult =
    explodeLeft(level)
      .orElse(explodeRight(level))
      .orElse(explodeThis(level))

  def explodeLeft(level: Int = 4): ExplosionResult =
    left.singleExplosion(level - 1) match {
      case Some(exploded, toLeft, toRight) =>
        val newRight = toRight.map(a => right.donateLeft(a)).getOrElse(right)
        Some((SnailfishPair(exploded, newRight), toLeft, None))
      case None => None
    }

  def explodeRight(level: Int = 4): ExplosionResult =
    right.singleExplosion(level - 1) match {
      case Some(exploded, toLeft, toRight) =>
        val newLeft = toLeft.map(a => left.donateRight(a)).getOrElse(left)
        Some((SnailfishPair(newLeft, exploded), None, toRight))
      case None => None
    }

  def explodeThis(level: Int = 4): ExplosionResult =
    if (level < 1)
      Some((SnailfishNumber.Zero, makeDonation(left), makeDonation(right)))
    else None

  def makeDonation(toDonate: SnailfishElement): Option[SnailfishNumber] =
    toDonate match {
      case _: SnailfishPair =>
        throw new Exception(
          "It should not be possible to be donating a SnailfishPair"
        )
      case sfn: SnailfishNumber => Some(sfn)
    }

  def donateLeft(donation: SnailfishNumber): SnailfishPair =
    SnailfishPair(left.donateLeft(donation), right)

  def donateRight(donation: SnailfishNumber): SnailfishPair =
    SnailfishPair(left, right.donateRight(donation))

  def split(): SnailfishPair =
    splitLeft()
      .orElse(splitRight())
      .getOrElse(this)

  def splitLeft(): Option[SnailfishPair] = {
    val newLeft = left.split()
    if (newLeft != left) Some(SnailfishPair(newLeft, right))
    else None
  }

  def splitRight(): Option[SnailfishPair] = {
    val newRight = right.split()
    if (newRight != left) Some(SnailfishPair(left, newRight))
    else None
  }

  override def magnitude: Long = 3 * left.magnitude + 2 * right.magnitude
}
