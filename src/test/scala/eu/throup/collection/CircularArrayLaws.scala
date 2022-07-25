package eu.throup
package collection

import cats.*
import cats.implicits.*
import cats.kernel.laws.discipline.*
import cats.laws.discipline.*
import org.scalacheck.{Arbitrary, Cogen, Gen}
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.typelevel.discipline.scalatest.FunSuiteDiscipline
/*

class CircularArrayLaws
    extends FunSuiteDiscipline
    with AnyFunSuiteLike
    with ScalaCheckPropertyChecks {

  implicit def eqCircularArray[A]: Eq[CircularArray[A]] = (x, y) =>
    x.toSeq === y.toSeq

  implicit def genCircularArray[A](implicit
      la: Arbitrary[List[A]]
  ): Gen[CircularArray[A]] = for {
    seq <- la.arbitrary
  } yield new CircularArray(seq)

  implicit def arbitraryCircularArray[A](implicit
      la: Arbitrary[List[A]]
  ): Arbitrary[CircularArray[A]] = Arbitrary { genCircularArray[A] }

  checkAll(
    "CircularArray.ApplyLaws",
    ApplyTests[CircularArray](summon[Apply[CircularArray]])
      .apply[Int, Int, String]
  )

  checkAll(
    "CircularArray.MonadLaws",
    MonadTests[CircularArray](summon[Monad[CircularArray]])
      .monad[Int, Int, String]
  )
}
 */
