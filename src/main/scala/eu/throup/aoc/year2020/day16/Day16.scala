package eu.throup.aoc.year2020.day16

import eu.throup.aoc.DayXX

object Day16 extends DayXX {
  override def part1(input: String): Long = {
    val theFields = fields(input)
    nearbyTickets(input)
      .map(errorRate(_, theFields))
      .sum
  }

  override def part2(input: String): Long = {
    val theFields = fields(input)
    val mine = myTicket(input)
    val valid = validTickets(input)

    val candidates: Array[Set[Int]] = theFields
      .map(f =>
        mine.indices.filter(i => valid.forall(t => f.range.contains(t(i))))
      )
      .map(_.toSet)

    var indexed: Map[Int, Set[Int]] = candidates.indices
      .map(i => i -> candidates(i))
      .toMap

    var toFind = mine.length
    var mapped: Map[Int, Field] = Map()
    while (toFind > 0) {
      val next: (Int, Set[Int]) =
        indexed.find({ case (_, s) => s.size == 1 }).get
      val index = next._1
      val found = next._2.head
      mapped += found -> theFields(index)
      indexed = indexed.mapValues(s => s - found).toMap
      toFind -= 1
    }

    mapped
      .filter({ case (_, f) => f.label.startsWith("departure ") })
      .keys
      .map(k => mine(k))
      .product
  }

  private def validTickets(input: String) = {
    val theFields = fields(input)
    nearbyTickets(input)
      .filter(t => isTicketValid(t, theFields))
  }

  private def fields(input: String) = {
    input
      .split("\n\n")(0)
      .split("\n")
      .map(Field(_))
  }

  private def myTicket(input: String) = {
    val mine = input
      .split("\n\n")(1)
      .split("\n")(1)

    parseTicket(mine)
  }

  private def nearbyTickets(input: String) = {
    input
      .split("\n\n")(2)
      .split("\n")
      .tail
      .map(parseTicket)
  }

  private def parseTicket(input: String) = {
    input.split(",").map(_.toLong)
  }

  private def errorRate(ticket: Seq[Long], fields: Seq[Field]): Long = {
    ticket.filter(f => !isFieldValid(f, fields)).sum
  }

  private def isTicketValid(ticket: Seq[Long], fields: Seq[Field]): Boolean = {
    ticket.forall(f => isFieldValid(f, fields))
  }

  private def isFieldValid(candidate: Long, fields: Seq[Field]) = {
    fields.exists(_.range.contains(candidate))
  }
}
