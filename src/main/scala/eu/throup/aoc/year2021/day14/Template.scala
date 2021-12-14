package eu.throup.aoc.year2021.day14

type Template = Map[Pair, Long]
extension(template: Template) {
  def *(m: Long): Template =
    template.map { case (pair, l) => pair -> l * m }
}
object Template {
  def apply(input: String): Template =
    Template(
      input.toCharArray
        .sliding(2)
        .map(s => (s(0), s(1)))
        .toSeq
    )

  def apply(seq: Seq[Pair]): Template =
    seq
      .groupBy(identity)
      .mapValues(_.size.toLong)
      .toMap
}
