package eu.throup.aoc

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

trait DayXXSpec extends AnyFreeSpec with Matchers {
  type returnType
  val testObject: DayXX

  "part1" - { runTestCases(testInput1, testObject.part1) }
  "part2" - { runTestCases(testInput2, testObject.part2) }

  private def runTestCases(
      results: Map[String, (String, returnType)],
      function: String => Any
  ) =
    "test cases" - {
      results.foreach((label, inputs) => {
        s"$label should return ${inputs._2}" in {
          function(inputs._1) shouldBe inputs._2
        }
      })
    }

  def testInput1: Map[String, (String, returnType)]
  def testInput2: Map[String, (String, returnType)]

  def loadResource(filename: String): String =
    Source
      .fromResource(resourcePath + filename)
      .getLines()
      .reduce(_ + "\n" + _)

  private def resourcePath: String =
    getClass.getPackage.getName
      .replace(".", "/") + "/"
}
