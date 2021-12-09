package eu.throup.aoc.year2020.day14

import eu.throup.aoc.DayXX

import scala.util.matching.Regex

object Day14 extends DayXX {
  val linePattern: Regex = "^([\\w\\[\\]]+)\\s*=\\s*(\\w+)$".r
  val memPattern: Regex = "^mem\\[(\\d+)\\]$".r

  override def part1(input: String): Long = {
    val lines = input.split("\n")
    var mask = Bitmask.None
    var mem: Map[Long, Long] = Map()
    for (line <- lines) {
      val a: Option[Regex.Match] = linePattern.findFirstMatchIn(line)
      val b: Regex.Match = a.get
      val c = b.group(1)
      val d = b.group(2)

      c match {
        case "mask" => mask = Bitmask(d)
        case _ => {
          val e = memPattern.findFirstMatchIn(c)
          val f = e.get
          val g = f.group(1).toLong
          mem += g -> mask.it(d.toLong)
        }
      }
    }
    mem.values.sum
  }

  override def part2(input: String): Long = {
    val lines = input.split("\n")
    var mask = Bitmask2.None
    var mem: Map[Long, Long] = Map()
    for (line <- lines) {
      val a: Option[Regex.Match] = linePattern.findFirstMatchIn(line)
      val b: Regex.Match = a.get
      val c = b.group(1)
      val d = b.group(2)

      c match {
        case "mask" => mask = Bitmask2(d)
        case _ => {
          val e = memPattern.findFirstMatchIn(c)
          val f = e.get
          val g = f.group(1).toLong

          val adds: Set[Long] = mask.it(g)

          val newMems: Map[Long, Long] = adds.map(a => a -> d.toLong).toMap
          mem = mem ++ newMems
        }
      }
    }
    mem.values.sum
  }
}
