package eu.throup.aoc.year2021.day14

import scala.annotation.tailrec

extension[A](map: Map[A, Long]) {
  def +(other: Map[A, Long]): Map[A, Long] =
    (map.toSeq ++ other.toSeq)
      .groupBy(_._1)
      .mapValues(_.map(_._2).sum)
      .toMap

  @tailrec
  def +(maps: Seq[Map[A, Long]]): Map[A, Long] =
    if (maps.isEmpty) map
    else ((map + maps.head) + maps.tail)
}
