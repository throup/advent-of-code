package eu.throup.measures

import ch.obermuhlner.math.big.BigDecimalMath
import eu.throup.measures.Angle.*
import eu.throup.measures.angle.{DegreeAngle, RadianAngle}

import java.math.{MathContext, RoundingMode}

abstract class Angle {
  def asRadians: BigDecimal
  def asDegrees: BigDecimal
  def asArcseconds: BigDecimal = asArcminutes * 60
  def asArcminutes: BigDecimal = asDegrees * 60
  def asTurns: BigDecimal = asDegrees / 360
  def asGradians: BigDecimal = asDegrees * 10 / 9

  def asDms: Dms = {
    val degrees: Int = asDegrees.toInt
    val _stepMinutes = (asDegrees - degrees) * 60
    val minutes: Int = _stepMinutes.toInt
    val seconds: Double = ((_stepMinutes - minutes) * 60).toDouble
    Dms(degrees, minutes, seconds)
  }

  def unary_+ : Angle = this
  def unary_- : Angle = newObject(-value)

  def *(x: Double): Angle = newObject(value * x)
  def *(x: BigInt): Angle = newObject(value * x.toLong)
  def *(x: BigDecimal): Angle = newObject(value * x)

  def +(x: Angle): Angle
  def -(x: Angle): Angle

  /* The division methods are convenient, but only return one possible candidate.
   * For example, an angle of π radians divided by 2 could be π/2 radians, and that is the answer you will receive from
   * these methods. But the answer could also be 3π/2 because of modular arithmetic.
   */
  def /(x: Double): Angle = newObject(value / x)
  def /(x: BigInt): Angle = newObject(value / x.toLong)
  def /(x: BigDecimal): Angle = newObject(value / x)

  /** returns the number of times the other angle will fit into this angle */
  def /(x: Angle): BigDecimal

  def sin: BigDecimal = BigDecimalMath.sin(asRadians.bigDecimal, mc)
  def cos: BigDecimal = BigDecimalMath.cos(asRadians.bigDecimal, mc)
  def tan: BigDecimal = BigDecimalMath.tan(asRadians.bigDecimal, mc)

  override def equals(other: Any): Boolean = other match {
    case that: Angle =>
      canEqual(that) &&
        (that canEqual this) &&
        asRadians == that.asRadians &&
        asDegrees == that.asDegrees
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Angle]

  override def hashCode(): Int = {
    val state = Seq(asRadians, asDegrees)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  protected def value: BigDecimal

  protected def newObject(value: BigDecimal): Angle

  protected def norm: Angle

  // Interoperability functions for Java
  def plus(x: Angle): Angle = this + (x)
  def minus(x: Angle): Angle = this - (x)
}

object Angle {
  val dc: MathContext = new MathContext(64, RoundingMode.HALF_EVEN);
  val mc: MathContext = new MathContext(128, RoundingMode.HALF_EVEN);
  val rc: MathContext = new MathContext(32, RoundingMode.HALF_EVEN);

  val PiValue: BigDecimal = BigDecimalMath.pi(mc)
  val π: Angle = degrees(180)
  val Pi: Angle = π
  val Zero: Angle = degrees(0)
  val RightAngle: Angle = degrees(90)

  val QuarterTurn: Angle = RightAngle
  val HalfTurn: Angle = π
  val FullTurn: Angle = Zero

  def radians(rad: BigDecimal): Angle = degrees(radiansToDegrees(rad))
  def radiansExact(rad: BigDecimal): Angle = new RadianAngle(rad).norm
  def radiansOfPi(radOfPi: BigDecimal): Angle = π * radOfPi
  def turns(tur: BigDecimal): Angle = degrees(tur * 360)
  def gradians(gra: BigDecimal): Angle = degrees(gra / 10 * 9)
  def degrees(deg: BigDecimal): Angle = new DegreeAngle(deg).norm
  def dms(deg: BigDecimal, min: BigDecimal, sec: BigDecimal): Angle =
    degrees(deg) + arcminutes(min) + arcseconds(sec)
  def arcseconds(sec: BigDecimal): Angle = arcminutes(sec / 60)
  def arcminutes(min: BigDecimal): Angle = degrees(min / 60)

  def asin(s: BigDecimal): Angle = radiansExact(BigDecimalMath.asin(s.bigDecimal, mc))
  def acos(c: BigDecimal): Angle = radiansExact(BigDecimalMath.acos(c.bigDecimal, mc))
  def atan(t: BigDecimal): Angle = radiansExact(BigDecimalMath.atan(t.bigDecimal, mc))
  def atan(x: BigDecimal, y: BigDecimal): Angle = atan2(y, x)
  def atan2(y: BigDecimal, x: BigDecimal): Angle = radiansExact(BigDecimalMath.atan2(y.bigDecimal, x.bigDecimal, mc))

  def radiansToDegrees(radians: BigDecimal, quantisation: MathContext = rc): BigDecimal = {
    val t = radians * 180 / PiValue
    t.round(quantisation)
  }

  def degreesToRadians(degrees: BigDecimal, quantisation: MathContext = dc): BigDecimal = {
    val t = degrees / 180 * PiValue
    t.round(quantisation)
  }

  case class Dms(degrees: Int, minutes: Int, seconds: Double)
}
