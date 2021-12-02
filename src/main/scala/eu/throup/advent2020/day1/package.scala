package eu.throup.advent2020

import com.typesafe.scalalogging.Logger

import scala.util.control.Breaks.{break, breakable}

package object day1 {
  private val Log = Logger(this.getClass)

  def solution(input: Seq[Int]): Int = {
    val sorted: Seq[Int] = input.sortWith(_ < _)

    var frontPointer = 0
    var backPointer = sorted.length - 1
    while (sorted(frontPointer) + sorted(backPointer) != 2020) {
      while (sorted(frontPointer) + sorted(backPointer) > 2020) {
        backPointer -= 1
      }
      frontPointer += 1
    }

    sorted(frontPointer) * sorted(backPointer)
  }

  def solution3(input: Seq[Int]): Int = {
    val sorted: Seq[Int] = input.sortWith(_ < _)

    var frontPointer = 0
    var midPointer = 1
    var backPointer = sorted.length - 1

    def isValid(p: Int) = {
      p >= 0 && p < sorted.length
    }
    while (isValid(frontPointer) && isValid(midPointer) && isValid(backPointer) && (sorted(frontPointer) + sorted(midPointer) + sorted(backPointer) != 2020)) {
      breakable {
        for (i <- (frontPointer + 1) until sorted.length - 2) {
          for (j <- (frontPointer + 2) until sorted.length - 1) {
            backPointer = j
            midPointer = i
            if (isValid(frontPointer) && isValid(midPointer) && isValid(backPointer) && (sorted(frontPointer) + sorted(midPointer) + sorted(backPointer) == 2020))
              break
          }
        }
        frontPointer += 1
      }
    }
    Log.info("frontPointer = {} => {}", frontPointer, sorted(frontPointer))
    Log.info("midPointer = {} => {}", midPointer, sorted(midPointer))
    Log.info("backPointer = {} => {}", backPointer, sorted(backPointer))
    sorted(frontPointer) * sorted(midPointer) * sorted(backPointer)
  }
}
