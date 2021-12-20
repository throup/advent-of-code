package eu.throup.aoc.year2021.day19

import eu.throup.aoc.year2021.day19.BeaconGroup.offsetBetweenCentres
import eu.throup.aoc.year2021.day19.Transformation.transforms

import scala.annotation.tailrec

type BeaconGroup = Set[Beacon]

extension(set: BeaconGroup) {
  def centralise(): BeaconGroup = {
    val (mx, my, mz) = findCorrection()
    set.map(b => Beacon(b.x - mx, b.y - my, b.z - mz))
  }

  def findCorrection(): (Int, Int, Int) = {
    val c = average
    (c.x, c.y, c.z)
  }

  @tailrec
  def offsetToOtherGroup(
      to: BeaconGroup,
      offset: Beacon = Beacon(0, 0, 0)
  ): Beacon = {
    if (to.map(_ - offset) == set) offset
    else {
      val extraOffset = set.offsetBetweenCentres(to.map(_ - offset))
      offsetToOtherGroup(to, offset + extraOffset)
    }
  }

  def average: Beacon = {
    val a = realAverage
    Beacon(
      a._1.toInt,
      a._2.toInt,
      a._3.toInt
    )
  }

  def realAverage: (Double, Double, Double) = {
    val c = set.toSeq.size.toDouble
    (
      set.toSeq.map(_.x.toLong).sum / c,
      set.toSeq.map(_.y.toLong).sum / c,
      set.toSeq.map(_.z.toLong).sum / c
    )
  }

  def centredOnOrigin: BeaconGroup = set.map(_ - set.average)

  def offsetBetweenCentres(to: BeaconGroup): Beacon =
    BeaconGroup.offsetBetweenCentres(set, to)

  def transformationTo(b: BeaconGroup): Beacon => Beacon =
    transforms
      .map(t => t -> b.centredOnOrigin.map(t(_)))
      .filter(_._2 == set.centredOnOrigin)
      .head
      ._1


  def fingerprint: Fingerprint = {
    val pairs = set.toSeq.combinations(2).map(s => (s(0), s(1)))
    pairs.map(s => s._1 distanceTo s._2).toSeq.sorted
  }
}

object BeaconGroup {
  def offsetBetweenCentres(from: BeaconGroup, to: BeaconGroup): Beacon = {
    val fromMean = from.realAverage
    val toMean = to.realAverage
    Beacon(
      (toMean._1 - fromMean._1).round.toInt,
      (toMean._2 - fromMean._2).round.toInt,
      (toMean._3 - fromMean._3).round.toInt
    )
  }
}
