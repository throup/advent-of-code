package eu.throup.advent2020

import scala.util.matching.Regex

package object day4 {
  val requiredKeys = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  def part1(input: String): Int = {
    parsePassports(input)
      .count(_ == 7)
  }

  def parsePassports(input: String): Array[Int] = {
    input.split("\n\n")
      .map(parseAndCountPassport)
  }

  def parseAndCountPassport(input: String): Int = {
    parsePassport(input)
      .count(g => requiredKeys.contains(g._1))
  }

  def parsePassport(input: String): Array[(String, String)] = {
    input.split("\\s")
      .flatMap(parsePassportField)
  }

  def parsePassportField(input: String): Map[String, String] = {
    val parsed = input.split(":\\s*")
    Map(parsed(0) -> parsed(1))
  }

  // ---

  def part2(input: String): Int = {
    validatePassports(input)
      .count(_ == 7)
  }

  def validatePassports(input: String): Array[Int] = {
    input.split("\n\n")
      .map(validatePassport)
  }

  def validatePassport(input: String): Int = {
    parsePassport(input)
      .filter(validateField)
      .count(g => requiredKeys.contains(g._1))
  }

  def validateField(field: (String, String)): Boolean = {
    val key = field._1
    val value = field._2
    key match {
      case "byr" => validateNumRange(value, 1920, 2002)
      case "iyr" => validateNumRange(value, 2010, 2020)
      case "eyr" => validateNumRange(value, 2020, 2030)
      case "hgt" => validateHeight(value)
      case "hcl" => validateHex(value)
      case "ecl" => validateColour(value)
      case "pid" => validatePid(value)
      case _ => false
    }
  }

  private def validateNumRange(value: String, from: Int, to: Int) = {
    value.toInt >= from && value.toInt <= to
  }

  private def validateHeight(value: String) = {
    val pattern: Regex = "^(\\d+)\\s*(\\w+)$".r
    val oMatches: Option[Regex.Match] = pattern.findFirstMatchIn(value)
    val matches: Regex.Match = oMatches.get
    val num: Int = matches.group(1).toInt
    val unit: String = matches.group(2)

    unit match {
      case "cm" => num >= 150 && num <= 193
      case "in" => num >= 59 && num <= 76
      case _ => false
    }
  }

  private def validateHex(value: String): Boolean = {
    val pattern: Regex = "^#([a-f0-9]{6})$".r
    pattern.matches(value)
  }

  private def validateColour(value: String): Boolean = {
    Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
  }

  private def validatePid(value: String): Boolean = {
    val pattern = "^([0-9]{9})$".r
    pattern.matches(value)
  }
}
