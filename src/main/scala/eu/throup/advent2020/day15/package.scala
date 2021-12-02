package eu.throup.advent2020

package object day15 {
  def part1(input: String): Long = {
    doTheWork(input, 2020)
  }

  // ---

  def part2(input: String): Long = {
    doTheWork(input, 30000000)
  }

  // ---

  def doTheWork(input: String, target: Long): Long = {
    // Extract the input string into a sequence of numbers.
    val start = input.split(",").map(_.toLong)

    // Prepare the trackers!
    // tracker1 := keys are the numbers from the sequence
    //             values are the step number when we last saw it
    // tracker2 := keys are the numbers from the sequence
    //             values are the step number when we last saw it before the step in tracker1
    var tracker1: scala.collection.mutable.Map[Long, Long] = scala.collection.mutable.Map.empty
    var tracker2: scala.collection.mutable.Map[Long, Long] = scala.collection.mutable.Map.empty
    start.indices.foreach(i => tracker1 += start(i) -> (i + 1).toLong)

    // Seed this value for the loop.
    // Tracks the last number we saw in the sequence.
    var last = start(start.length - 1)

    for (i: Long <- (start.length + 1).toLong to target) {
      // Determine the next "last" number based on the previous two occasions we saw the
      // current "last" number.
      // tracker2.getOrElse is to account for this being our first encounter with the number.
      last = tracker1(last) - tracker2.getOrElse(last, tracker1(last))

      // Shift any existing record from tracker1 into tracker2...
      if (tracker1.contains(last)) {
        tracker2 += (last -> tracker1(last))
      }
      // ... and place a new record in tracker1
      tracker1 += (last -> i)
    }

    // Return the current value of "last" -- this is our target number.
    last
  }
}
