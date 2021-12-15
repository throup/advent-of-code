package eu.throup.aoc

import org.scalatest.concurrent.TimeLimits
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.Span
import org.scalatest.time.SpanSugar.*

import scala.concurrent.duration.Duration
import scala.io.Source

trait DayXXSpec extends AnyFreeSpec with Matchers with TimeLimits {
  type returnType
  val testObject: DayXX

  "part1" - { runTestCases(testInput1, limits1, testObject.part1) }
  "part2" - { runTestCases(testInput2, limits2, testObject.part2) }

  private def runTestCases(
      results: Map[String, (String, returnType)],
      limits: Map[String, Span],
      function: String => Any
  ) =
    "test cases" - {
      results.foreach((label, inputs) => {
        val limit: Option[Span] = limits.get(label)
        s"$label should return ${inputs._2}" in {
          val result = limit match {
            case Some(d: Span) => failAfter(d) { function(inputs._1) }
            case _             => function(inputs._1)
          }
          (result shouldBe inputs._2)
        }
      })
    }

  def testInput1: Map[String, (String, returnType)]
  def testInput2: Map[String, (String, returnType)]

  def limits1: Map[String, Span] = Map()
  def limits2: Map[String, Span] = Map()

  def loadResource(filename: String): String =
    Source
      .fromResource(resourcePath + filename)
      .getLines()
      .reduce(_ + "\n" + _)

  private def resourcePath: String =
    getClass.getPackage.getName
      .replace(".", "/") + "/"
}
