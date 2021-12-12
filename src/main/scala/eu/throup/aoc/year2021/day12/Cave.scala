package eu.throup.aoc.year2021.day12

sealed trait Cave
object Cave {
  def apply(label: String): Cave =
    label.trim match {
      case "start"                 => Start
      case "end"                   => End
      case l if l.toUpperCase == l => Big(label)
      case _                       => Small(label)
    }

  case class Big(label: String) extends Cave
  case class Small(label: String) extends Cave
  object Start extends Small("start")
  object End extends Small("end")
}
