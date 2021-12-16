package eu.throup.aoc.year2021.day16

type Bit = Int
type BitSequence = Seq[Bit]
extension (bits: BitSequence){
  def toInt: Int = bits.mkString.bin.toInt
  def toLong: Long = bits.mkString.bin
}

object BitSequence {
  def unit: BitSequence = BitSequence()

  def apply(bits: Bit*): BitSequence = Seq(bits *)

  def fromHexString(hex: String): BitSequence =
    hex.toCharArray
      .map(_.hex)
      .map(_.toBinaryString.reverse.padTo(4, '0').reverse)
      .flatMap(_.toCharArray)
      .map(_ - 48)
}
