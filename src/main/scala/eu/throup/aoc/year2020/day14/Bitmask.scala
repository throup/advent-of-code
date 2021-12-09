package eu.throup.aoc.year2020.day14

import java.lang.Long.parseLong
import scala.language.postfixOps
import scala.util.matching.Regex

case class Bitmask(mask: String) {
  private val masky0 = parseLong(mask.replace('X', '1'), 2)
  private val masky1 = parseLong(mask.replace('X', '0'), 2)

  def it(i: Long): Long = (i & masky0) | masky1
}
object Bitmask {
  var None: Bitmask = Bitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
}

case class Bitmask2(mask: String) {
  private val masky1 = parseLong(mask.replace('X', '0'), 2)

  private val flips: Set[Long] = mask.indices
    .map(i => (mask.length - i - 1) -> mask(i))
    .filter({ case (_, c) => c == 'X' })
    .map({ case (i, _) => math.pow(2, i) })
    .map(_.toLong)
    .toSet

  def it(i: Long): Set[Long] = {
    val step1 = i | masky1

    var collect: Set[Long] = Set(step1)
    for (flip <- flips) {
      val ss = collect.map(l => flip | l)
      val sv = collect.map(l => (~flip) & l)
      collect = ss ++ sv
    }

    if (collect.isEmpty) {
      Set(step1)
    } else {
      collect
    }
  }
}
object Bitmask2 {
  var None: Bitmask2 = Bitmask2("000000000000000000000000000000000000")
}
