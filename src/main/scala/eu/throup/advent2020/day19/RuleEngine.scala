package eu.throup.advent2020.day19

import scala.language.postfixOps
import scala.util.matching.Regex

class RuleEngine(input: String) {
  private val sections = input.split("\n\n")
  private var rules = parseRules(sections(0))
  private val messages = parseMessages(sections(1))
  private val maxLength = messages.map(_.length).max

  doSimplifyRules()

  private def doSimplifyRules(): Unit = {
    var size = rules.size + 1 // For the fun of it
    while (rules.size != size) {
      size = rules.size
      simplifyRules()
    }
  }

  private def simplifyRules(): Unit = {
    val filtered = rules.filter({case (_, a) => isSimpleRule(a)}).filter({case (k, _) => k != "0"})

    filtered
      .foreach({case (k, a) =>
      rules = rules.mapValues(_.map(_.map(c => if (c == k) genRegexForRule(a, k) else c) ) ).filterKeys(!filtered.contains(_)).toMap
    })
  }

  private def isSimpleRule(a: Array[Array[String]]) = {
    a.forall(_.forall(!rules.contains(_)))
  }

  def solve: Int = {
    messages.count(regexForRule(0).matches(_))
  }

  def regexForRule(i: Int): Regex = {
    "^(" + regexForRule(i.toString) + ")$" r
  }

  private def parseMessages(section: String) = {
    section.split("\n")
  }

  private def parseRules(section: String) = {
    section.split("\n")
      .map(_.split(": "))
      .map(p => p(0) -> p(1))
      .toMap
      .mapValues(parseRule)
      .toMap
  }

  private def parseRule(input: String) = {
    input.split("\\|")
      .map(_.trim)
      .map(_.split("\\s+"))
  }

  private def regexForRule(s: String): String = {
    genRegexForRule(rules(s), s)
  }

  private def genRegexForRule(rule: Array[Array[String]], s: String): String = {
    val strings = rule.map(regexForRulePart(_, s))
    if (strings.length > 1) {
      "(" + strings.mkString("|") + ")"
    } else {
      strings.head
    }
  }

  private def regexForRulePart(rulePart: Array[String], ruleId: String): String = {
    val strings = rulePart
      .map(regexForRuleBit(_, ruleId))
      .filter(_ != "")

    if (rulePart.contains(ruleId)) {
      stupidLoop(strings).mkString("|")
    } else {
      strings.mkString("")
    }
  }

  private def stupidLoop(strings: Array[String]): Array[String] = {
    (1 to maxLength).map(i => {
      strings.map(s => {
        "(" + s + "){" + i + "}"
      }).mkString("")
    }).toArray
  }

  private def regexForRuleBit(ruleBit: String, ruleId: String): String = {
    if (rules.contains(ruleBit)) {
      if (ruleBit != ruleId) {
        "(" + regexForRule(ruleBit) + ")"
      } else {
        ""
      }
    } else {
      ruleBit.replace("\"", "")
    }
  }
}
