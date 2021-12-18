package eu.throup.aoc.year2021.day18

import eu.throup.aoc.year2021.day18.SnailfishElement.ExplosionResult

case class SnailfishNumber(magnitude: Long) extends SnailfishElement {
  def singleExplosion(level: Int = 4): ExplosionResult = None

  def donateLeft(donation: SnailfishNumber): SnailfishNumber =
    donate(donation)

  def donateRight(donation: SnailfishNumber): SnailfishNumber =
    donate(donation)

  def donate(donation: SnailfishNumber): SnailfishNumber =
    SnailfishNumber(magnitude + donation.magnitude)

  def split(): SnailfishElement =
    if (magnitude > 9) {
      SnailfishPair(
        SnailfishNumber(magnitude / 2),
        SnailfishNumber((magnitude + 1) / 2)
      )
    } else this
}

object SnailfishNumber {
  def Zero: SnailfishNumber = SnailfishNumber(0)
}
