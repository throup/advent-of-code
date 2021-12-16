package eu.throup.aoc.year2021.day16

import eu.throup.aoc.DayXX

object Day16 extends DayXX {
  override def part1(input: String) = {
    val packet: Packet = parseInput(input)
    packet.versionSum
  }

  override def part2(input: String) = {
    val packet: Packet = parseInput(input)
    packet.value
  }

  def parseInput(input: String): Packet =
    Packet(
      BitSequence.fromHexString(input)
    )
}
