package eu.throup.aoc.year2021.day19

import eu.throup.aoc.year2021.day19.Scanner.viewPointTransformation

import scala.annotation.tailrec

type Fingerprint = Seq[Int]

case class Scanner(
    label: String,
    rawBeacons: Set[Beacon],
    viewPoints: Seq[(Beacon, Beacon => Beacon)] = Seq((Beacon(0, 0, 0), identity))
) {
  def newViewpoint(
      offset: Beacon,
      transformation: Beacon => Beacon
  ): Scanner = {
    Scanner(
      label,
      rawBeacons,
      viewPoints :+ (offset, transformation)
    )
  }

  def offset: Beacon = viewPoints.map(_._1).reduce(_ + _)

  lazy val transformation: Beacon => Beacon = viewPointTransformation(viewPoints)
  lazy val beacons: Set[Beacon] = rawBeacons.map(transformation)

  private val beaconFingerprints: Seq[(Set[Beacon], Fingerprint)] =
    beacons.toSeq
    .combinations(3)
    .map(_.toSet)
    .map(s => s -> s.fingerprint)
    .toSeq

  lazy val fingerprints: Set[Fingerprint] =
    beaconFingerprints
      .map(_._2)
      .toSet

  def beaconCountsForFingerprints(fs: Set[Fingerprint]) =
    beaconFingerprints
      .filter(x => fs.contains(x._2))
      .flatMap(x => x._1)
      .groupBy(identity)
      .mapValues(_.size)
      .toSeq
      .sortBy(_._2)
      .reverse

  lazy val allDistances: Seq[Int] =
    rawBeacons.toSeq
      .combinations(2)
      .map(a => a.head distanceTo a.tail.head)
      .toSeq
      .sorted

  def sharedFingerprints(other: Scanner): Set[Fingerprint] =
    fingerprints intersect other.fingerprints
}

object Scanner {
  def apply(input: String): Scanner = {
    val lines = input.split("\n")
    Scanner(
      lines.head.trim.drop(12).take(2).trim,
      lines.tail.map(Beacon(_)).toSet
    )
  }

  @tailrec
  def viewPointTransformation(viewPoints: Seq[(Beacon, Beacon => Beacon)], transformation: Beacon => Beacon = identity): Beacon => Beacon = {
    if (viewPoints.isEmpty) transformation
    else
      viewPointTransformation(
        viewPoints.tail,
        (beacon: Beacon) => viewPoints.head._2(transformation(beacon)) - viewPoints.head._1
      )
  }
}
