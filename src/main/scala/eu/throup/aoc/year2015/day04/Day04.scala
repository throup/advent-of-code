package eu.throup.aoc.year2015.day04

import eu.throup.aoc.DayXX
import eu.throup.measures.Point

import java.security.MessageDigest

object Day04 extends DayXX {
  override def part1(input: String): Int = {
    val y = for {
      i <- LazyList.from(0).take(1049000)
      token = input + i
      hash = MessageDigest
        .getInstance("MD5")
        .digest(token.getBytes)
        .take(5)
        .map("%02x".format(_))
        .mkString
      cs = hash.take(5)
      if cs.forall(_ == '0')
    } yield i
    y.head
  }

  override def part2(input: String): Int = {
    val y = for {
      i <- LazyList.from(0).take(9999999)
      token = input + i
      hash = MessageDigest
        .getInstance("MD5")
        .digest(token.getBytes)
        .take(6)
        .map("%02x".format(_))
        .mkString
      cs = hash.take(6)
      if cs.forall(_ == '0')
    } yield i
    y.head
  }
}
