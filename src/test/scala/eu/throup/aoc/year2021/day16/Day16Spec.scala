package eu.throup.aoc.year2021.day16

import eu.throup.aoc.DayXXSpec

class Day16Spec extends DayXXSpec {
  override type returnType = Long
  override val testObject = Day16

  override def testInput1 = Map(
    sampleInput -> (sampleInput, 6),
    sample2Input -> (sample2Input, 9),
    sample3Input -> (sample3Input, 14),
    sample4Input -> (sample4Input, 16),
    sample5Input -> (sample5Input, 12),
    sample6Input -> (sample6Input, 23),
    sample7Input -> (sample7Input, 31),
    sample8Input -> (sample8Input -> 14),
    sample9Input -> (sample9Input -> 8),
    sample10Input -> (sample10Input -> 15),
    sample11Input -> (sample11Input -> 11),
    sample12Input -> (sample12Input -> 13),
    sample13Input -> (sample13Input -> 19),
    sample14Input -> (sample14Input -> 16),
    sample15Input -> (sample15Input -> 20),
    "puzzle input" -> (puzzleInput, 969)
  )

  override def testInput2 = Map(
    sampleInput -> (sampleInput, 2021),
    sample2Input -> (sample2Input, 1),
    sample3Input -> (sample3Input, 3),
    sample4Input -> (sample4Input, 15),
    sample5Input -> (sample5Input, 46),
    sample6Input -> (sample6Input, 46),
    sample7Input -> (sample7Input, 54),
    sample8Input -> (sample8Input -> 3),
    sample9Input -> (sample9Input -> 54),
    sample10Input -> (sample10Input -> 7),
    sample11Input -> (sample11Input -> 9),
    sample12Input -> (sample12Input -> 1),
    sample13Input -> (sample13Input -> 0),
    sample14Input -> (sample14Input -> 0),
    sample15Input -> (sample15Input -> 1),
    "puzzle input" -> (puzzleInput, 124921618408L)
  )

  def sampleInput = "D2FE28"
  def sample2Input = "38006F45291200"
  def sample3Input = "EE00D40C823060"
  def sample4Input = "8A004A801A8002F478"
  def sample5Input = "620080001611562C8802118E34"
  def sample6Input = "C0015000016115A2E0802F182340"
  def sample7Input = "A0016C880162017C3686B18A3D4780"
  def sample8Input = "C200B40A82"
  def sample9Input = "04005AC33890"
  def sample10Input = "880086C3E88112"
  def sample11Input = "CE00C43D881120"
  def sample12Input = "D8005AC2A8F0"
  def sample13Input = "F600BC2D8F"
  def sample14Input = "9C005AC2F8F0"
  def sample15Input = "9C0141080250320F1802104A08"
  def puzzleInput = loadResource("puzzleinput.txt")
}
