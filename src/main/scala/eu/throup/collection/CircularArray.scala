package eu.throup.collection

import scala.collection.IterableOnce
import scala.reflect.ClassTag

class CircularArray[A: ClassTag](seed: Seq[A], offset: Int = 0) {
  lazy val toArray: Array[A] = internal.slice(start, length) ++ internal.slice(0, start)
  private lazy val doubleArray: Array[A] = toArray ++ toArray
  private val internal: Array[A] = seed.toArray
  private val start: Int = {
    val j = offset % length
    if (j < 0) j + length else j
  }

  def indexOf(elem: A, from: Long = 0): Int = {
    val k = absoluteIndex(from - start)
    val i = doubleArray.indexOf(elem, k)
    i % length
  }

  def lastIndexOf(elem: A, end: Int = internal.length - 1): Int = {
    0
  }

  def shiftRight(steps: Long = 1): CircularArray[A] = {
    shiftLeft(-steps)
  }

  def shiftLeft(steps: Long = 1): CircularArray[A] = {
    new CircularArray(internal, absoluteIndex(steps))
  }

  def insertAt[B >: A : ClassTag](i: Long, x: B): CircularArray[B] = {
    val k = absoluteIndex(i)
    new CircularArray[B](
      internal.slice(0, k) ++
        Array(x) ++
        internal.slice(k, length)
    )
  }

  def insertAfter[B >: A : ClassTag](s: A, x: B): CircularArray[B] = {
    insertAt(indexOf(s) + 1, x)
  }

  def insertBefore[B >: A : ClassTag](s: A, x: B): CircularArray[B] = {
    insertAt(indexOf(s), x)
  }

  def insertAllAfter[B >: A : ClassTag](s: A, xs: IterableOnce[B]): CircularArray[B] = {
    val array = xs.toArray
    insertAllAt(indexOf(s) + 1, array)
  }

  def insertAllBefore[B >: A : ClassTag](s: A, xs: IterableOnce[B]): CircularArray[B] = {
    val array = xs.toArray
    insertAllAt(indexOf(s), array)
  }

  def insertAllAt[B >: A : ClassTag](i: Long, xs: IterableOnce[B]): CircularArray[B] = {
    val k = absoluteIndex(i)
    new CircularArray[B](
      internal.slice(0, k) ++
        xs.toArray ++
        internal.slice(k, length)
    )
  }

  def appended[B >: A : ClassTag](x: B): CircularArray[B] = {
    new CircularArray(internal.appended(x))
  }

  @`inline` final def :++[B >: A : ClassTag](suffix: IterableOnce[B]): CircularArray[B] = appendedAll(suffix)

  @`inline` final def :++[B >: A : ClassTag](suffix: Array[_ <: B]): CircularArray[B] = appendedAll(suffix)

  @`inline` final def concat[B >: A : ClassTag](suffix: IterableOnce[B]): CircularArray[B] = appendedAll(suffix)

  @`inline` final def concat[B >: A : ClassTag](suffix: Array[_ <: B]): CircularArray[B] = appendedAll(suffix)

  @`inline` final def ++[B >: A : ClassTag](xs: IterableOnce[B]): CircularArray[B] = appendedAll(xs)

  @`inline` final def ++[B >: A : ClassTag](xs: Array[_ <: B]): CircularArray[B] = appendedAll(xs)

  def appendedAll[B >: A : ClassTag](suffix: IterableOnce[B]): CircularArray[B] = {
    new CircularArray(internal.appendedAll(suffix.toArray))
  }

  @`inline` final def +:[B >: A : ClassTag](x: B): CircularArray[B] = prepended(x)

  def prepended[B >: A : ClassTag](x: B): CircularArray[B] = {
    new CircularArray(internal.prepended(x))
  }

  @`inline` final def ++:[B >: A : ClassTag](prefix: IterableOnce[B]): CircularArray[B] = prependedAll(prefix)

  @`inline` final def ++:[B >: A : ClassTag](prefix: Array[_ <: B]): CircularArray[B] = prependedAll(prefix)

  def prependedAll[B >: A : ClassTag](suffix: IterableOnce[B]): CircularArray[B] = {
    new CircularArray(internal.prependedAll(suffix.toArray))
  }

  def this(length: Int) = {
    this(new Array[A](length))
  }

  def apply(i: Long): A = {
    internal(absoluteIndex(i))
  }

  def update(i: Long, x: A): CircularArray[A] = {
    val k = absoluteIndex(i)
    val n = internal.clone()
    n.update(k, x)
    new CircularArray(n, start)
  }

  private def absoluteIndex(i: Long): Int = {
    val j = ((i + start) % length).toInt
    if (j < 0) j + length else j
  }

  def length: Int = internal.length

  def toSeq: Seq[A] = toArray.toSeq

  def slice(from: Long, until: Long): CircularArray[A] = {
    new CircularArray(arraySlice(from, until))
  }

  def arraySlice(from: Long, until: Long): Array[A] = {
    val ff = absoluteIndex(from)
    val uu = absoluteIndex(until)
    if (ff < uu) {
      internal.slice(ff, uu)
    } else {
      internal.slice(ff, length) ++ internal.slice(0, uu)
    }
  }

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
    val slice: Array[A] = foc.arraySlice((length - sliceSize).toInt, length)
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
  def apply[T: ClassTag](xs: T*): CircularArray[T] = {
    new CircularArray(xs, 0)
  }
}
