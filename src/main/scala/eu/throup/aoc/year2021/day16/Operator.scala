package eu.throup.aoc.year2021.day16

trait Operator {
  def apply(seq: Seq[Packet]): Long
}
object Operator {
  val Zero: Operator = _ => 0
  val Sum: Operator = _.map(_.value).sum
  val Product: Operator = _.map(_.value).product
  val Minimum: Operator = _.map(_.value).min
  val Maximum: Operator = _.map(_.value).max
  val Greater: Operator = seq => {
    val vals = seq.map(_.value)
    if (vals(0) > vals(1)) 1 else 0
  }
  val Less: Operator = seq => {
    val vals = seq.map(_.value)
    if (vals(0) < vals(1)) 1 else 0
  }
  val Equal: Operator = seq => {
    val vals = seq.map(_.value)
    if (vals(0) == vals(1)) 1 else 0
  }
}
