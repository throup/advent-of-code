package eu.throup.aoc.year2021.day12

case class Path(a: Cave, b: Cave) {
  lazy val reverse: Path = Path(b, a)
}
