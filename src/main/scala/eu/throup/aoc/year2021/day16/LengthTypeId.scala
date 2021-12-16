package eu.throup.aoc.year2021.day16

enum LengthTypeId(val count: Int) {
  case BitLength extends LengthTypeId(15)
  case PacketLength extends LengthTypeId(11)
}
