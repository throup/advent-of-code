package eu.throup.aoc.year2021.day12

case class Route(paths: Seq[Path]) {
  lazy val end: Cave = paths.reverse.head.b
  lazy val caves: Seq[Cave] = paths.map(_.b)
  def :+(path: Path) = Route(paths :+ path)
}
object Route {
  def apply(path: Path): Route = Route(Seq(path))
}
