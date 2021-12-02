package eu.throup.advent2020

package object day13 {
  def part1(input: String) = {
    val (start, ids) = parseInput(input)

    val (id, time) = ids
      .filter(_.nonEmpty)
      .map(_.get)
      .map(i => (i, nextBusFromTime(i, start)))
      .minBy(_._2)

    (time - start) * id
  }

  private def nextBusFromTime(id: BigInt, time: BigInt) : BigInt = {
    val d = time / id
    val last = d * id
    if (d == last) last else last + id
  }

  // ---

  def part2(input: String) = {
    val (_, ids) = parseInput(input)

    val indexedIds = ids.indices
      .map(i => BigInt(i) -> ids(i))
      .toMap
      .filter(_._2.nonEmpty)
      .mapValues(_.get)

    var workingSet = indexedIds.toList.map(s => (s._1, s._2))
    while(workingSet.length > 1) {
      val firstTwo = workingSet.slice(0, 2)
      val product = firstTwo.map(_._2).product
      val meetingPoint = chineseRemainder(-firstTwo(0)._1, firstTwo(0)._2, -firstTwo(1)._1, firstTwo(1)._2)

      workingSet = (List((meetingPoint, product)) ++ workingSet.slice(2, workingSet.length))
    }

    workingSet.head._2 - workingSet.head._1
  }

  // ---

  def chineseRemainder(val1: BigInt, mod1: BigInt, val2: BigInt, mod2: BigInt): BigInt = {
    val ans = mod1 * (val2 - val1) * modInverse(mod1, mod2) + val1
    val prod = mod1 * mod2

    var f = -ans % prod

    while (f < 0)
      f += prod
    f
  }

  private def parseInput(input: String): (BigInt, Array[Option[BigInt]]) = {
    val splitInput = input.split("\n")
    (
      splitInput(0).toLong,
      splitInput(1).split(",").map(extractId)
    )
  }

  private def extractId(in: String): Option[BigInt] = {
    try {
      Some(Integer.parseInt(in.trim))
    } catch {
      case e: NumberFormatException => None
    }
  }

  /**
   * This is STOLEN CODE :-(
   * We're into real number theory territory now, and this is a function to return
   * the inverse of a number within a modulo space. I could (and did) implement a
   * version of my own which just wasn't performant enough, so this implementation
   * of the function has been shamelessly pilfoured from the internet. However, I
   * can still sleep at night because I had to work out for myself how to actually
   * apply it to the problem at hand.
   */
  def modInverse( inA: BigInt,  inM: BigInt): BigInt = {
    var m = inM
    var a = inA
    var m0: BigInt = m
    var y: BigInt = 0
    var x: BigInt = 1

    if (m == 1)
      return 0;

    while (a > 1) {
      // q is quotient
      val q: BigInt = a / m

      var t: BigInt = m

      // m is remainder now, process
      // same as Euclid's algo
      m = a % m
      a = t
      t = y

      // Update x and y
      y = x - q * y
      x = t
    }

    // Make x positive
    while (x < 0)
      x += m0

    x
  }
}
