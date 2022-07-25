package eu.throup.collection

import scala.collection.IterableOnce
import scala.reflect.ClassTag
/*

class CircularArray[A](seed: Seq[A], offset: Int = 0) {
  def toArray[C >: A: ClassTag]: Array[C] =
    internalArray.slice(start, length) ++ internalArray.slice(0, start)
  lazy val toSeq: Seq[A] = seed.slice(start, length) ++ seed.slice(0, start)
  private lazy val doubleSeq: Seq[A] = toSeq ++ toSeq
  private def internalArray[C >: A: ClassTag]: Array[C] = seed.toArray
  private val start: Int = {
    val j = if (length > 0) (offset % length) else 0
    if (j < 0) j + length else j
  }

  def indexOf(elem: A, from: Long = 0): Int = {
    val k = absoluteIndex(from - start)
    val i = doubleSeq.indexOf(elem, k)
    i % length
  }

  def lastIndexOf(elem: A, end: Int = seed.length - 1): Int = {
    0
  }

  def shiftRight(steps: Long = 1): CircularArray[A] = {
    shiftLeft(-steps)
  }

  def shiftLeft(steps: Long = 1): CircularArray[A] = {
    new CircularArray(seed, absoluteIndex(steps))
  }

  def insertAt[B >: A](i: Long, x: B): CircularArray[B] = {
    val k = absoluteIndex(i)
    new CircularArray[B](
      seed.slice(0, k) ++
        Seq(x) ++
        seed.slice(k, length)
    )
  }

  def insertAfter[B >: A](s: A, x: B): CircularArray[B] = {
    insertAt(indexOf(s) + 1, x)
  }

  def insertBefore[B >: A](s: A, x: B): CircularArray[B] = {
    insertAt(indexOf(s), x)
  }

  def insertAllAfter[B >: A](
      s: A,
      xs: IterableOnce[B]
  ): CircularArray[B] = {
    insertAllAt(indexOf(s) + 1, xs)
  }

  def insertAllBefore[B >: A](
      s: A,
      xs: IterableOnce[B]
  ): CircularArray[B] = {
    insertAllAt(indexOf(s), xs)
  }

  def insertAllAt[B >: A](
      i: Long,
      xs: IterableOnce[B]
  ): CircularArray[B] = {
    val k = absoluteIndex(i)
    new CircularArray[B](
      seed.slice(0, k) ++
        xs ++
        seed.slice(k, length)
    )
  }

  def appended[B >: A](x: B): CircularArray[B] = {
    new CircularArray(seed.appended(x))
  }

  @`inline` final def :++[B >: A](
      suffix: IterableOnce[B]
  ): CircularArray[B] = appendedAll(suffix)

  @`inline` final def :++[B >: A](
      suffix: Array[_ <: B]
  ): CircularArray[B] = appendedAll(suffix)

  @`inline` final def concat[B >: A](
      suffix: IterableOnce[B]
  ): CircularArray[B] = appendedAll(suffix)

  @`inline` final def concat[B >: A](
      suffix: Array[_ <: B]
  ): CircularArray[B] = appendedAll(suffix)

  @`inline` final def ++[B >: A](
      xs: IterableOnce[B]
  ): CircularArray[B] = appendedAll(xs)

  @`inline` final def ++[B >: A](
      xs: Array[_ <: B]
  ): CircularArray[B] = appendedAll(xs)

  def appendedAll[B >: A](
      suffix: IterableOnce[B]
  ): CircularArray[B] = {
    new CircularArray(seed.appendedAll(suffix))
  }

  @`inline` final def +:[B >: A](x: B): CircularArray[B] = prepended(
    x
  )

  def prepended[B >: A](x: B): CircularArray[B] = {
    new CircularArray(seed.prepended(x))
  }

  @`inline` final def ++:[B >: A](
      prefix: IterableOnce[B]
  ): CircularArray[B] = prependedAll(prefix)

  @`inline` final def ++:[B >: A](
      prefix: Array[_ <: B]
  ): CircularArray[B] = prependedAll(prefix)

  def prependedAll[B >: A](
      suffix: IterableOnce[B]
  ): CircularArray[B] = {
    new CircularArray(seed.prependedAll(suffix))
  }

  def this(length: Int, empty: A) = {
    this(Seq.fill(length)(empty))
  }

  def apply(i: Long): A = {
    seed(absoluteIndex(i))
  }

  def update(i: Long, x: A): CircularArray[A] = {
    val k = absoluteIndex(i)
    val n = seed.updated(k, x)
    new CircularArray(n, start)
  }

  private def absoluteIndex(i: Long): Int = {
    val j = ((i + start) % length).toInt
    if (j < 0) j + length else j
  }

  def length: Int = seed.length

  def slice(from: Long, until: Long): CircularArray[A] = {
    new CircularArray(seqSlice(from, until))
  }

  def seqSlice(from: Long, until: Long): Seq[A] = {
    val ff = absoluteIndex(from)
    val uu = absoluteIndex(until)
    if (ff < uu) {
      seed.slice(ff, uu)
    } else {
      seed.slice(ff, length) ++ seed.slice(0, uu)
    }
  }

  def arraySlice[C >: A: ClassTag](from: Long, until: Long): Array[C] =
    seqSlice(from, until).toArray

  def moveSliceTo(from: Long, until: Long, dest: Long): CircularArray[A] = {
    val sliceSize = until - from
    val foc = shiftLeft(until)

    val samSize = dest - from
    val moveDistance = samSize.abs
    val newDest = if (dest < from) {
      (samSize + length - 3) % length
    } else {
      (samSize + length) % length
    }

    val widg: CircularArray[A] = foc.slice(0, (length - sliceSize).toInt)
    val slice: Seq[A] = foc.seqSlice((length - sliceSize).toInt, length)
    val googoo = widg.insertAllAt(newDest, slice)

    val moveLeft = moveDistance > length - moveDistance

    val newOffset = if (moveLeft) {
      if (dest < from) {
        until
      } else {
        from - sliceSize
      }
    } else {
      if (dest < from) {
        from + sliceSize
      } else {
        from
      }
    }
    googoo.shiftRight(newOffset)
  }

  def moveSliceAfter(from: Long, until: Long, dest: A): CircularArray[A] = {
    val loc = indexOf(dest) + from - until + 1
    moveSliceTo(from, until, loc)
  }

  def shiftTo(target: A): CircularArray[A] = {
    if (this(0) == target) {
      this
    } else {
      shiftLeft(indexOf(target))
    }
  }
}

object CircularArray {
  def apply[T](xs: T*): CircularArray[T] = {
    new CircularArray(xs, 0)
  }
}

import cats.*
import cats.implicits.*

trait ApplyCircularArray extends Apply[CircularArray] {
  override def ap[A, B](ff: CircularArray[A => B])(
      fa: CircularArray[A]
  ): CircularArray[B] = {
    val ffs = ff.toSeq
    val fas = fa.toSeq

    val sss: Seq[B] = Apply[Seq].ap(ffs)(fas)
    new CircularArray(sss)
  }
}

trait ApplicativeCircularArray extends Applicative[CircularArray] {
  override def pure[A](x: A): CircularArray[A] = CircularArray(x)
}

trait FlatMapCircularArray extends FlatMap[CircularArray] {
  override def flatMap[A, B](fa: CircularArray[A])(
      f: A => CircularArray[B]
  ): CircularArray[B] = {
    val sa: Seq[A] = fa.toSeq
    val scab: Seq[CircularArray[B]] = sa.map(f)
    val sb: Seq[B] = scab.flatMap(_.toSeq)
    new CircularArray(sb)
  }

  override def tailRecM[A, B](a: A)(
      f: A => CircularArray[Either[A, B]]
  ): CircularArray[B] = ???
}

given Monad[CircularArray] =
  new Monad[CircularArray]
    with FlatMapCircularArray
    with ApplicativeCircularArray
    with ApplyCircularArray
 */
