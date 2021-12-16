package eu.throup.aoc.year2021.day16

import scala.annotation.tailrec

sealed trait Packet {
  def sequence: BitSequence
  lazy val header: BitSequence = sequence.take(6)
  lazy val body: BitSequence = sequence.drop(6)
  def discard: BitSequence

  lazy val version: Int = header.take(3).toInt
  lazy val typeId: TypeId = TypeId.fromOrdinal(header.drop(3).toInt)

  def value: Long
  def versionSum: Long
}

object Packet {
  def apply(sequence: BitSequence): Packet = {
    typeFromSequence(sequence) match {
      case TypeId.Literal => ValuePacket(sequence)
      case _              => OperatorPacket(sequence)
    }
  }

  def typeFromSequence(sequence: BitSequence): TypeId =
    TypeId.fromOrdinal(
      sequence.take(6).drop(3).toInt
    )
}

case class ValuePacket(sequence: BitSequence) extends Packet {
  lazy val value: Long = calculateValue(body)
  lazy val discard: BitSequence = identifyDiscard(body)

  lazy val versionSum: Long = version

  @tailrec
  private def calculateValue(
      remaining: BitSequence,
      collected: BitSequence = BitSequence.unit
  ): Long =
    if (remaining.head == 0)
      (collected ++ remaining.drop(1).take(4)).toLong
    else
      calculateValue(
        remaining.drop(5),
        collected ++ remaining.drop(1).take(4)
      )

  @tailrec
  private def identifyDiscard(remaining: BitSequence): BitSequence =
    if (remaining.head == 0) remaining.drop(5)
    else identifyDiscard(remaining.drop(5))
}

abstract class OperatorPacket(sequence: BitSequence) extends Packet {
  lazy val value: Long = typeId.operator(subpackets)
  lazy val discard: BitSequence =
    subpackets.reverse.head.discard ++ localDiscard
  lazy val versionSum: Long = version + subpackets.map(_.versionSum).sum

  lazy val lengthTypeId: LengthTypeId = LengthTypeId.fromOrdinal(body.head)

  def subpackets: Seq[Packet]
  def localDiscard: BitSequence

  val subLength: Int = body.drop(1).take(lengthTypeId.count).toInt

  def identifySubPackets(
      sequence: BitSequence,
      collected: Seq[Packet] = Seq(),
      limit: Option[Long] = None
  ): Seq[Packet] =
    if (limit.fold(false)(_ == 0) || sequence.isEmpty) collected
    else
      identifySubPackets(
        Packet(sequence).discard,
        collected ++ Seq(Packet(sequence)),
        limit.map(_ - 1)
      )
}

object OperatorPacket {
  def apply(sequence: BitSequence): OperatorPacket =
    lengthTypeFromSequence(sequence) match {
      case LengthTypeId.BitLength    => BitLengthOperatorPacket(sequence)
      case LengthTypeId.PacketLength => PacketLengthOperatorPacket(sequence)
    }

  def lengthTypeFromSequence(sequence: BitSequence): LengthTypeId =
    LengthTypeId.fromOrdinal(
      sequence.drop(6).head
    )
}

case class BitLengthOperatorPacket(sequence: BitSequence)
    extends OperatorPacket(sequence) {
  lazy val localDiscard: BitSequence =
    body.drop(1).drop(lengthTypeId.count).drop(subLength)

  lazy val subpackets: Seq[Packet] = {
    val subPackets: BitSequence =
      body.drop(1).drop(lengthTypeId.count).take(subLength)
    identifySubPackets(subPackets)
  }
}

case class PacketLengthOperatorPacket(sequence: BitSequence)
    extends OperatorPacket(sequence) {
  lazy val localDiscard: BitSequence = BitSequence.unit

  lazy val subpackets: Seq[Packet] = {
    val subPackets: BitSequence = body.drop(1).drop(lengthTypeId.count)
    identifySubPackets(subPackets, limit = Some(subLength))
  }
}
