package eu.throup.aoc.year2020.day14

import org.scalatest.freespec.AnyFreeSpec

class BitmapSpec extends AnyFreeSpec {
  "Bitmask" - {
    "with a single 1 can mask that value" - {
      val mask = Bitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1")
      "mask of 0 is 1" in assert(mask.it(0) == 1)
      "mask of 1 is 1" in assert(mask.it(1) == 1)
      "mask of 2 is 3" in assert(mask.it(2) == 3)
      "mask of 3 is 3" in assert(mask.it(3) == 3)
      "mask of 34359738368 is 34359738369" in assert(
        mask.it(34359738368L) == 34359738369L
      )
      "mask of 34359738369 is 34359738369" in assert(
        mask.it(34359738369L) == 34359738369L
      )
    }
    "with a single 0 can mask that value" - {
      val mask = Bitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX0")
      "mask of 0 is 0" in assert(mask.it(0) == 0)
      "mask of 1 is 0" in assert(mask.it(1) == 0)
      "mask of 2 is 2" in assert(mask.it(2) == 2)
      "mask of 3 is 2" in assert(mask.it(3) == 2)
      "mask of 34359738368 is 34359738368" in assert(
        mask.it(34359738368L) == 34359738368L
      )
      "mask of 34359738369 is 34359738368" in assert(
        mask.it(34359738369L) == 34359738368L
      )
    }
  }

  "Bitmask2" - {
    "with mask" - {
      val mask = Bitmask2("000000000000000000000000000000X1001X")
      "mask of 42" in {
        val output: Set[Long] = mask.it(42)
        assert(output == Set(26, 27, 58, 59))
      }
    }
  }
}
