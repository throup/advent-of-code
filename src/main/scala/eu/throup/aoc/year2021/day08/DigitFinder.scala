package eu.throup.aoc.year2021.day08

case class DigitFinder(inputs: Set[DigInput]) {
  lazy val toDigits: Map[DigInput, Digit] =
    Map(
      d0 -> Digit._0,
      d1 -> Digit._1,
      d2 -> Digit._2,
      d3 -> Digit._3,
      d4 -> Digit._4,
      d5 -> Digit._5,
      d6 -> Digit._6,
      d7 -> Digit._7,
      d8 -> Digit._8,
      d9 -> Digit._9
    )

  private lazy val groups = inputs.groupBy(_.size)

  private lazy val d0 = Set(cA, cB, cC, cE, cF, cG)
  private lazy val d1 = groups(2).head
  private lazy val d2 = Set(cA, cC, cD, cE, cG)
  private lazy val d3 = Set(cA, cC, cD, cF, cG)
  private lazy val d4 = groups(4).head
  private lazy val d5 = Set(cA, cB, cD, cF, cG)
  private lazy val d6 = Set(cA, cB, cD, cE, cF, cG)
  private lazy val d7 = groups(3).head
  private lazy val d8 = groups(7).head
  private lazy val d9 = Set(cA, cB, cC, cD, cF, cG)

  private lazy val cA = d7.diff(d1).head
  private lazy val cB = d4.diff(d1).-(cD).head
  private lazy val cC = occurrencesIn069
    .filter { case (_, s) => s == 2 }
    .keys
    .filter(_ != cD)
    .filter(_ != cE)
    .head
  private lazy val cD = occurrencesIn235
    .filter { case (_, s) => s == 3 }
    .keys
    .filter(d4.contains(_))
    .head
  private lazy val cE = occurrencesIn235
    .filter { case (_, s) => s == 1 }
    .keys
    .filter(_ != cB)
    .head
  private lazy val cF = d1.-(cC).head
  private lazy val cG = d8.diff(d4).diff(d7).-(cE).head

  private lazy val occurrencesIn235 =
    groups(5).toSeq
      .flatMap(_.toSeq)
      .groupBy(identity)
      .map { case (i, s) => i -> s.size }

  private lazy val occurrencesIn069 =
    groups(6).toSeq
      .flatMap(_.toSeq)
      .groupBy(identity)
      .map { case (i, s) => i -> s.size }
}
