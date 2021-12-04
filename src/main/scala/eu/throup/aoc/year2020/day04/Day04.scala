package eu.throup.aoc.year2020.day04

import eu.throup.aoc.DayXX

import scala.util.matching.Regex

object Day04 extends DayXX {
  val requiredKeys = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  override def part1(input: String): Int = {
    parsePassports(input)
      .count(_ == 7)
  }

  def parsePassports(input: String): Array[Int] =
    input
      .split("\n\n")
      .map(parseAndCountPassport)

  def parseAndCountPassport(input: String): Int =
    parsePassport(input)
      .count { (key, _) => requiredKeys.contains(key) }

  def parsePassport(input: String): Array[(String, String)] =
    input
      .split("\\s")
      .flatMap(parsePassportField)

  def parsePassportField(input: String): Map[String, String] = {
    val parsed = input.split(":\\s*")
    Map(parsed(0) -> parsed(1))
  }

  // ---

  override def part2(input: String): Int =
    validatePassports(input)
      .count(_ == 7)

  def validatePassports(input: String): Array[Int] =
    input
      .split("\n\n")
      .map(validatePassport)

  def validatePassport(input: String): Int =
    parsePassport(input)
      .filter(validateField)
      .count { (key, _) => requiredKeys.contains(key) }

  def validateField(field: (String, String)): Boolean = {
    val (key, value) = field
    key match {
      case "byr" => validateNumRange(value, 1920, 2002)
      case "iyr" => validateNumRange(value, 2010, 2020)
      case "eyr" => validateNumRange(value, 2020, 2030)
      case "hgt" => validateHeight(value)
      case "hcl" => validateHex(value)
      case "ecl" => validateColour(value)
      case "pid" => validatePid(value)
      case _     => false
    }
  }

  private def validateNumRange(value: String, min: Int, max: Int) =
    (min to max) contains value.toInt

  private def validateHeight(value: String) = {
    val pattern: Regex = "^(\\d+)\\s*(\\w+)$".r
    val oMatches: Option[Regex.Match] = pattern.findFirstMatchIn(value)
    val matches: Regex.Match = oMatches.get
    val num: Int = matches.group(1).toInt
    val unit: String = matches.group(2)

    unit match {
      case "cm" => num >= 150 && num <= 193
      case "in" => num >= 59 && num <= 76
      case _    => false
    }
  }

  private def validateHex(value: String): Boolean =
    "^#([a-f0-9]{6})$".r.matches(value)

  private def validateColour(value: String): Boolean =
    Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)

  private def validatePid(value: String): Boolean =
    "^([0-9]{9})$".r.matches(value)
}
