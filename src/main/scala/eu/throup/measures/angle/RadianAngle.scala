package eu.throup.measures.angle

import eu.throup.measures.Angle
import eu.throup.measures.Angle.{PiValue, radiansExact, radiansToDegrees}

private[measures] class RadianAngle(protected val value: BigDecimal) extends Angle {
  override def asRadians: BigDecimal = value
  override def asDegrees: BigDecimal = radiansToDegrees(value)

  override def +(x: Angle): Angle = radiansExact(value + x.asRadians)
  override def -(x: Angle): Angle = radiansExact(value - x.asRadians)

  /** returns the number of times the other angle will fit into this angle */
  override def /(x: Angle): BigDecimal = value / x.asRadians

  override def toString: String = s"${Angle}(${value}ᶜ)"

  override def norm: Angle = {
    val normed = value % (2 * PiValue)
    if (normed < 0) {
      new RadianAngle(normed + 2 * PiValue)
    } else {
      new RadianAngle(normed)
    }
  }

  override protected def newObject(value: BigDecimal): Angle = radiansExact(value)
}
