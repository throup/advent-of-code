package eu.throup.aoc.year2021.day19

import eu.throup.aoc.DayXX
import eu.throup.aoc.year2021.day19.Transformation.transforms

import scala.annotation.tailrec
import scala.util.Random

object Day19 extends DayXX {
  override def part1(input: String) = {
    val scanners: Seq[Scanner] = parseInput(input)

    val scannerMap: Map[String, Scanner] =
      scanners
        .groupBy(_.label)
        .mapValues(_.head)
        .toMap

    val mergesToPerform: Seq[(String, String)] =
      identifyOverlappingScanners(scanners)
        .map { case (a, b) => a.label -> b.label }
        .flatMap(o => Seq(o, o.swap))

    iterateAlignmentAttempts(mergesToPerform, scannerMap)
      .flatMap(_.beacons)
      .toSet
      .size
  }

  override def part2(input: String) = {
    val scanners: Seq[Scanner] = parseInput(input)

    val scannerMap: Map[String, Scanner] =
      scanners
        .groupBy(_.label)
        .mapValues(_.head)
        .toMap

    val mergesToPerform: Seq[(String, String)] =
      identifyOverlappingScanners(scanners)
        .map { case (a, b) => a.label -> b.label }
        .flatMap(o => Seq(o, o.swap))

    iterateAlignmentAttempts(mergesToPerform, scannerMap)
      .map(_.offset)
      .combinations(2)
      .toSeq
      .map(s => s(0) manhattanDistanceTo s(1))
      .max
  }

  @tailrec
  def iterateAlignmentAttempts(
      mergesToPerform: Seq[(String, String)],
      scannerMap: Map[String, Scanner],
      _sorted: Set[String] = Set()
  ): Seq[Scanner] = {
    val sorted = if (_sorted.isEmpty) Set(mergesToPerform.head._1) else _sorted
    if (mergesToPerform.isEmpty) scannerMap.values.toSeq
    else {
      val toProcess = mergesToPerform.filter(e => sorted.contains(e._1)).head
      val theRest = mergesToPerform.filter(_ != toProcess)

      val newMap =
        attemptToAlign(scannerMap(toProcess._1), scannerMap(toProcess._2))
          .fold(scannerMap)(tr => {
            scannerMap + (toProcess._2 -> tr)
          })

      iterateAlignmentAttempts(theRest, newMap, sorted + toProcess._2)
    }
  }

  def attemptToAlign(a: Scanner, b: Scanner): Option[Scanner] = {
    val sharedFingerprints: Set[Fingerprint] = a sharedFingerprints b

    val aBeaconCount = a.beaconCountsForFingerprints(sharedFingerprints)
    val bBeaconCount = b.beaconCountsForFingerprints(sharedFingerprints)

    if (aBeaconCount.size < 12 || bBeaconCount.size < 11) None
    else {
      val sharedBeaconsFromA: BeaconGroup =
        aBeaconCount.take(12).map(_._1).toSet
      val sharedBeaconsFromB: BeaconGroup =
        bBeaconCount.take(12).map(_._1).toSet

      val transformAToB: Beacon => Beacon =
        sharedBeaconsFromA transformationTo sharedBeaconsFromB

      val transformedBeaconsFromB: Set[Beacon] =
        sharedBeaconsFromB.map(transformAToB)

      val offset = sharedBeaconsFromA offsetToOtherGroup transformedBeaconsFromB

      Some(b.newViewpoint(offset, transformAToB))
    }
  }

  def identifyOverlappingScanners(
      scanners: Seq[Scanner]
  ): Seq[(Scanner, Scanner)] = {
    scanners
      .combinations(2)
      .toSeq
      .map(pair => {
        val first = pair.head
        val second = pair.tail.head
        pair -> (first.allDistances intersect second.allDistances)
      })
      .filter(_._2.size > 65)
      .sortBy(_._2.size)
      .reverse
      .map(a => (a._1(0) -> a._1(1)))
  }

  def parseInput(input: String): Seq[Scanner] =
    input.trim
      .split("\n\n")
      .map(Scanner(_))
}
