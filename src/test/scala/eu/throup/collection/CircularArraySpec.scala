package eu.throup.collection

import eu.throup.circular.{*, given}
import eu.throup.circular.CircularArraySeq.{*, given}
import org.scalatest.freespec.AnyFreeSpec

class CircularArraySpec extends AnyFreeSpec {
  "Construct it" - {
    "Construct with an array" in {
      val c = CircularArraySeq(Array(1, 2, 3, 4, 5, 6, 7, 8))
      assert(c.length == 8)
    }
    "Construct with parameter sequence" in {
      val c = CircularArraySeq(1, 2, 3, 4, 5, 6, 7, 8)
      assert(c.length == 8)
    }
  }
  "Fetching values" - {
    val c = CircularArraySeq(3, 4, 5, 6)
    "within real range" in {
      assert(c(0) == 3)
      assert(c(1) == 4)
      assert(c(2) == 5)
      assert(c(3) == 6)
    }
    "after real range" in {
      assert(c(4) == 3)
      assert(c(5) == 4)
      assert(c(6) == 5)
      assert(c(7) == 6)
      assert(c(4444) == 3)
      assert(c(4445) == 4)
      assert(c(4446) == 5)
      assert(c(4447) == 6)
    }
    "before real range" in {
      assert(c(-1) == 6)
      assert(c(-2) == 5)
      assert(c(-3) == 4)
      assert(c(-4) == 3)
      assert(c(-4441) == 6)
      assert(c(-4442) == 5)
      assert(c(-4443) == 4)
      assert(c(-4444) == 3)
    }
  }
  "Converting it" - {
    val c = CircularArraySeq(3, 4, 5, 6)
    "original array" - {
      "to Array" in {
        val a: Array[Int] = c.toArray
        assert(a sameElements Array(3, 4, 5, 6))
      }
      "to Seq" in {
        val a: Seq[Int] = c.toSeq
        assert(a == Seq(3, 4, 5, 6))
      }
    }
    "shifted array" - {
      val d = c.shiftLeft(3)
      "to Array" in {
        val a: Array[Int] = d.toArray
        assert(a sameElements Array(6, 3, 4, 5))
      }
      "to Seq" in {
        val a: Seq[Int] = d.toSeq
        assert(a == Seq(6, 3, 4, 5))
      }
    }
  }
  "Inserting values" - {
    val c = CircularArraySeq(3, 4, 5, 6)
    "single values" - {
      "At the tail" in {
        val d = c.appended(7)
        assert(d(-2) == 6)
        assert(d(-1) == 7)
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 5)
        assert(d(3) == 6)
        assert(d(4) == 7)
        assert(d(5) == 3)
      }
      "At the head" in {
        val d = c.prepended(7)
        assert(d(-2) == 5)
        assert(d(-1) == 6)
        assert(d(0) == 7)
        assert(d(1) == 3)
        assert(d(2) == 4)
        assert(d(3) == 5)
        assert(d(4) == 6)
        assert(d(5) == 7)
      }
      "At a specific position" in {
        val d = c.insertAt(3, 7)
        assert(d(-2) == 7)
        assert(d(-1) == 6)
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 5)
        assert(d(3) == 7)
        assert(d(4) == 6)
        assert(d(5) == 3)
      }
      "Replacing values" in {
        val d: CircularArraySeq[Int] = c(2) = 8
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 8)
        assert(d(3) == 6)
        assert(d(4) == 3)
        assert(c(0) == 3)
        assert(c(1) == 4)
        assert(c(2) == 5)
        assert(c(3) == 6)
        assert(c(4) == 3)
      }
      "After ___" in {
        val d = c.insertAfter(5, 8)
        assert(d(-2) == 8)
        assert(d(-1) == 6)
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 5)
        assert(d(3) == 8)
        assert(d(4) == 6)
        assert(d(5) == 3)
      }
      "Before ___" in {
        val d = c.insertBefore(5, 8)
        assert(d(-2) == 5)
        assert(d(-1) == 6)
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 8)
        assert(d(3) == 5)
        assert(d(4) == 6)
        assert(d(5) == 3)
      }
    }
    "multiple values" - {
      "as sequence" - {
        "At the tail" in {
          val d = c.appendedAll(Seq(9, 12, 24))
          assert(d(-2) == 12)
          assert(d(-1) == 24)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 5)
          assert(d(3) == 6)
          assert(d(4) == 9)
          assert(d(5) == 12)
        }
        "At the head" in {
          val d = c.prependedAll(Seq(9, 12, 24))
          assert(d(-2) == 5)
          assert(d(-1) == 6)
          assert(d(0) == 9)
          assert(d(1) == 12)
          assert(d(2) == 24)
          assert(d(3) == 3)
          assert(d(4) == 4)
          assert(d(5) == 5)
        }
        "At a specific position" in {
          val d = c.insertAllAt(3, Seq(9, 12, 24))
          assert(d(-2) == 24)
          assert(d(-1) == 6)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 5)
          assert(d(3) == 9)
          assert(d(4) == 12)
          assert(d(5) == 24)
        }
        "After ___" in {
          val d = c.insertAllAfter(5, Seq(9, 12, 24))
          assert(d(-2) == 24)
          assert(d(-1) == 6)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 5)
          assert(d(3) == 9)
          assert(d(4) == 12)
          assert(d(5) == 24)
        }
        "Before ___" in {
          val d = c.insertAllBefore(5, Seq(9, 12, 24))
          assert(d(-2) == 5)
          assert(d(-1) == 6)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 9)
          assert(d(3) == 12)
          assert(d(4) == 24)
          assert(d(5) == 5)
        }
      }
      "as array" - {
        "At the tail" in {
          val d = c.appendedAll(Array(9, 12, 24))
          assert(d(-2) == 12)
          assert(d(-1) == 24)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 5)
          assert(d(3) == 6)
          assert(d(4) == 9)
          assert(d(5) == 12)
        }
        "At the head" in {
          val d = c.prependedAll(Array(9, 12, 24))
          assert(d(-2) == 5)
          assert(d(-1) == 6)
          assert(d(0) == 9)
          assert(d(1) == 12)
          assert(d(2) == 24)
          assert(d(3) == 3)
          assert(d(4) == 4)
          assert(d(5) == 5)
        }
        "At a specific position" in {
          val d = c.insertAllAt(3, Array(9, 12, 24))
          assert(d(-2) == 24)
          assert(d(-1) == 6)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 5)
          assert(d(3) == 9)
          assert(d(4) == 12)
          assert(d(5) == 24)
        }
        "After ___" in {
          val d = c.insertAllAfter(5, Seq(9, 12, 24))
          assert(d(-2) == 24)
          assert(d(-1) == 6)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 5)
          assert(d(3) == 9)
          assert(d(4) == 12)
          assert(d(5) == 24)
        }
        "Before ___" in {
          val d = c.insertAllBefore(5, Seq(9, 12, 24))
          assert(d(-2) == 5)
          assert(d(-1) == 6)
          assert(d(0) == 3)
          assert(d(1) == 4)
          assert(d(2) == 9)
          assert(d(3) == 12)
          assert(d(4) == 24)
          assert(d(5) == 5)
        }
      }
    }
  }
  "Shifting values" - {
    val c = CircularArraySeq(3, 4, 5, 6)
    "shift left" - {
      "shift by 1" in {
        val d = c.shiftLeft()
        assert(d(-5) == 3)
        assert(d(-4) == 4)
        assert(d(-3) == 5)
        assert(d(-2) == 6)
        assert(d(-1) == 3)
        assert(d(0) == 4)
        assert(d(1) == 5)
        assert(d(2) == 6)
        assert(d(3) == 3)
        assert(d(4) == 4)
        assert(d(5) == 5)
      }
      "shift by 3" in {
        val d = c.shiftLeft(3)
        assert(d(-5) == 5)
        assert(d(-4) == 6)
        assert(d(-3) == 3)
        assert(d(-2) == 4)
        assert(d(-1) == 5)
        assert(d(0) == 6)
        assert(d(1) == 3)
        assert(d(2) == 4)
        assert(d(3) == 5)
        assert(d(4) == 6)
        assert(d(5) == 3)
      }
      "shift by the length" in {
        val d = c.shiftLeft(4)
        assert(d(-5) == 6)
        assert(d(-4) == 3)
        assert(d(-3) == 4)
        assert(d(-2) == 5)
        assert(d(-1) == 6)
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 5)
        assert(d(3) == 6)
        assert(d(4) == 3)
        assert(d(5) == 4)
      }
      "shift by something larger than the internal array" in {
        val d = c.shiftLeft(7)
        assert(d(-5) == 5)
        assert(d(-4) == 6)
        assert(d(-3) == 3)
        assert(d(-2) == 4)
        assert(d(-1) == 5)
        assert(d(0) == 6)
        assert(d(1) == 3)
        assert(d(2) == 4)
        assert(d(3) == 5)
        assert(d(4) == 6)
        assert(d(5) == 3)
      }
    }
    "shift right" - {
      "shift by 1" in {
        val d = c.shiftRight()
        assert(d(-5) == 5)
        assert(d(-4) == 6)
        assert(d(-3) == 3)
        assert(d(-2) == 4)
        assert(d(-1) == 5)
        assert(d(0) == 6)
        assert(d(1) == 3)
        assert(d(2) == 4)
        assert(d(3) == 5)
        assert(d(4) == 6)
        assert(d(5) == 3)
      }
      "shift by 3" in {
        val d = c.shiftRight(3)
        assert(d(-5) == 3)
        assert(d(-4) == 4)
        assert(d(-3) == 5)
        assert(d(-2) == 6)
        assert(d(-1) == 3)
        assert(d(0) == 4)
        assert(d(1) == 5)
        assert(d(2) == 6)
        assert(d(3) == 3)
        assert(d(4) == 4)
        assert(d(5) == 5)
      }
      "shift by the length" in {
        val d = c.shiftRight(4)
        assert(d(-5) == 6)
        assert(d(-4) == 3)
        assert(d(-3) == 4)
        assert(d(-2) == 5)
        assert(d(-1) == 6)
        assert(d(0) == 3)
        assert(d(1) == 4)
        assert(d(2) == 5)
        assert(d(3) == 6)
        assert(d(4) == 3)
        assert(d(5) == 4)
      }
      "shift by something larger than the internal array" in {
        val d = c.shiftRight(7)
        assert(d(-5) == 3)
        assert(d(-4) == 4)
        assert(d(-3) == 5)
        assert(d(-2) == 6)
        assert(d(-1) == 3)
        assert(d(0) == 4)
        assert(d(1) == 5)
        assert(d(2) == 6)
        assert(d(3) == 3)
        assert(d(4) == 4)
        assert(d(5) == 5)
      }
    }
  }
  "Locating items" - {
    val c = CircularArraySeq(3, 4, 5, 4, 6, 7, 4, 8)
    val d = c.shiftLeft(4)
    "indexOf" in {
      assert(c.indexOf(4) == 1)
      assert(d.indexOf(4) == 2)
    }
    "indexOf with from" in {
      assert(c.indexOf(4, -3) == 6)
      assert(c.indexOf(4, -2) == 6)
      assert(c.indexOf(4, -1) == 1)
      assert(c.indexOf(4, 0) == 1)
      assert(c.indexOf(4, 1) == 1)
      assert(c.indexOf(4, 2) == 3)
      assert(c.indexOf(4, 3) == 3)
      assert(c.indexOf(4, 4) == 6)
      assert(c.indexOf(4, 5) == 6)
      assert(c.indexOf(4, 6) == 6)
      assert(c.indexOf(4, 7) == 1)
      assert(c.indexOf(4, 8) == 1)
      assert(c.indexOf(4, 9) == 1)

      assert(d.indexOf(4, -3) == 5)
      assert(d.indexOf(4, -2) == 7)
      assert(d.indexOf(4, -1) == 7)
      assert(d.indexOf(4, 0) == 2)
      assert(d.indexOf(4, 1) == 2)
      assert(d.indexOf(4, 2) == 2)
      assert(d.indexOf(4, 3) == 5)
      assert(d.indexOf(4, 4) == 5)
      assert(d.indexOf(4, 5) == 5)
      assert(d.indexOf(4, 6) == 7)
      assert(d.indexOf(4, 7) == 7)
      assert(d.indexOf(4, 8) == 2)
      assert(d.indexOf(4, 9) == 2)
    }
  }
  "Slicing" - {
    val c = CircularArraySeq(1, 2, 3, 4, 5, 6, 7, 8)
    "moveSliceTo" - {
      "move to the same place" in {
        val d = c.moveSliceTo(2, 5, 2)
        assert(d.toSeq == Seq(1, 2, 3, 4, 5, 6, 7, 8))
      }
      "simple move right 1" in { //  t > d > f
        val d = c.moveSliceTo(2, 5, 3)
        assert(d.toSeq == Seq(1, 2, 6, 3, 4, 5, 7, 8))
      }
      "simple move right 2" in { //  d == t > f
        val d = c.moveSliceTo(2, 5, 4)
        assert(d.toSeq == Seq(1, 2, 6, 7, 3, 4, 5, 8))
      }
      "simple move right 3" in { //  d > t > f
        val d = c.moveSliceTo(2, 5, 5)
        assert(d.toSeq == Seq(1, 2, 6, 7, 8, 3, 4, 5))
      }
      "simple move right 4" in { //  d > t > f
        val d = c.moveSliceTo(2, 5, 6)
        assert(d.toSeq == Seq(5, 2, 6, 7, 8, 1, 3, 4))
      }
      "simple move right 5" in { //  d > t > f
        val d = c.moveSliceTo(2, 5, 7)
        assert(d.toSeq == Seq(4, 5, 6, 7, 8, 1, 2, 3))
      }
      "simple move right 6" in { // t > f > d
        val d = c.moveSliceTo(2, 5, 8)
        assert(d.toSeq == Seq(3, 4, 5, 7, 8, 1, 2, 6))
      }
      "simple move right 7" in { // t > f > d
        val d = c.moveSliceTo(2, 5, 9)
        assert(d.toSeq == Seq(7, 3, 4, 5, 8, 1, 2, 6))
      }
      "simple move left 1" in { // t > f > d
        val d = c.moveSliceTo(2, 5, 1)
        assert(d.toSeq == Seq(1, 3, 4, 5, 2, 6, 7, 8))
      }
      "simple move left 2" in {
        val d = c.moveSliceTo(2, 5, 0)
        assert(d.toSeq == Seq(3, 4, 5, 1, 2, 6, 7, 8))
      }
      "simple move left 3" in {
        val d = c.moveSliceTo(2, 5, -1)
        assert(d.toSeq == Seq(4, 5, 8, 1, 2, 6, 7, 3))
      }
      "simple move left 4" in {
        val d = c.moveSliceTo(2, 5, -2)
        assert(d.toSeq == Seq(5, 7, 8, 1, 2, 6, 3, 4))
      }
      "all in second cycle" in {
        val d = c.moveSliceTo(10, 13, 12)
        assert(d.toSeq == Seq(1, 2, 6, 7, 3, 4, 5, 8))
      }
      "overlapping slice move right 1" in {
        val d = c.moveSliceTo(6, 9, 7)
        assert(d.toSeq == Seq(8, 1, 3, 4, 5, 6, 2, 7))
      }
      "overlapping slice move right 2" in {
        val d = c.moveSliceTo(6, 9, 8)
        assert(d.toSeq == Seq(7, 8, 1, 4, 5, 6, 2, 3))
      }
      "overlapping slice move right 3" in {
        val d = c.moveSliceTo(6, 9, 9)
        assert(d.toSeq == Seq(4, 7, 8, 1, 5, 6, 2, 3)) //  7, 8, 1
      }
      "overlapping slice move right 4" in {
        val d = c.moveSliceTo(6, 9, 10)
        assert(d.toSeq == Seq(4, 5, 7, 8, 1, 6, 2, 3)) //  7, 8, 1
      }
      "overlapping slice move right 5" in {
        val d = c.moveSliceTo(6, 9, 11)
        assert(d.toSeq == Seq(4, 5, 6, 7, 8, 1, 2, 3)) //  7, 8, 1
      }
      "overlapping slice move right 6" in {
        val d = c.moveSliceTo(6, 9, 12)
        assert(d.toSeq == Seq(4, 5, 6, 2, 7, 8, 1, 3)) //  7, 8, 1
      }
      "overlapping slice don't move" in {
        val d = c.moveSliceTo(7, 10, 7)
        assert(d.toSeq == Seq(1, 2, 3, 4, 5, 6, 7, 8)) // 8, 1, 2
      }

      "overlapping slice move left 1" in {
        val d = c.moveSliceTo(7, 10, 6)
        assert(d.toSeq == Seq(2, 7, 3, 4, 5, 6, 8, 1)) // 8, 1, 2
      }
      "overlapping slice move left 2" in {
        val d = c.moveSliceTo(7, 10, 5)
        assert(d.toSeq == Seq(6, 7, 3, 4, 5, 8, 1, 2)) // 8, 1, 2
      }
      "overlapping slice move left 3" in {
        val d = c.moveSliceTo(7, 10, 4)
        assert(d.toSeq == Seq(6, 7, 3, 4, 8, 1, 2, 5)) // 8, 1, 2
      }
      "overlapping slice move left 4" in {
        val d = c.moveSliceTo(7, 10, 3)
        assert(d.toSeq == Seq(6, 7, 3, 8, 1, 2, 4, 5)) // 8, 1, 2
      }

      "move slice from left-shifted array to the same place" in {
        val d = c.shiftLeft(1)
        val e = d.moveSliceTo(2, 5, 2)
        assert(e.toSeq == Seq(2, 3, 4, 5, 6, 7, 8, 1))
      }
      "move slice from right-shifted array to the same place" in {
        val d = c.shiftRight(1)
        val e = d.moveSliceTo(2, 5, 2)
        assert(e.toSeq == Seq(8, 1, 2, 3, 4, 5, 6, 7))
      }
      /*
      "to in second cycle" in {
        val d = c.moveSliceTo(2, 13, 3)
        assert(d.toSeq == Seq(1, 2, 6, 3, 4, 5, 7, 8))
      }
      "from in earlier cycle" in {
        val d = c.moveSliceTo(-6, 5, 3)
        assert(d.toSeq == Seq(1, 2, 6, 3, 4, 5, 7, 8))
      }
       */
    }
  }
}
