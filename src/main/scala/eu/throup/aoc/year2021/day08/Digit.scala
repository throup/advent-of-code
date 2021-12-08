package eu.throup.aoc.year2021.day08

enum Digit(val value: Int, val segments: Set[Segment]) {
  case _0 extends Digit(0, Set(Segment.a, Segment.b, Segment.c, Segment.e, Segment.f, Segment.g))
  case _1 extends Digit(1, Set(Segment.c, Segment.f))
  case _2 extends Digit(2, Set(Segment.a, Segment.c, Segment.d, Segment.e, Segment.g))
  case _3 extends Digit(3, Set(Segment.a, Segment.c, Segment.d, Segment.f, Segment.g))
  case _4 extends Digit(4, Set(Segment.b, Segment.c, Segment.d, Segment.f))
  case _5 extends Digit(5, Set(Segment.a, Segment.b, Segment.d, Segment.f, Segment.g))
  case _6 extends Digit(6, Set(Segment.a, Segment.b, Segment.d, Segment.e, Segment.f, Segment.g))
  case _7 extends Digit(7, Set(Segment.a, Segment.c, Segment.f))
  case _8 extends Digit(8, Set(Segment.a, Segment.b, Segment.c, Segment.d, Segment.e, Segment.f, Segment.g))
  case _9 extends Digit(9, Set(Segment.a, Segment.b, Segment.c, Segment.d, Segment.f, Segment.g))
}

object Digit {
  val mapper: Map[Set[Segment], Digit] = Digit.values.map(d => d.segments -> d).toMap
  def identify(in: Set[Segment]): Option[Digit] = mapper.get(in)
}