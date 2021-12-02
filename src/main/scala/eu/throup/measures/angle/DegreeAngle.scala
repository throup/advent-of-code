package eu.throup.measures.angle

import eu.throup.measures.Angle
import eu.throup.measures.Angle.{degrees, degreesToRadians}

private[measures] class DegreeAngle(protected val value: BigDecimal) extends Angle {
  override def asRadians: BigDecimal = degreesToRadians(value)
  override def asDegrees: BigDecimal = value

  override def +(x: Angle): Angle = degrees(value + x.asDegrees)
  override def -(x: Angle): Angle = degrees(value - x.asDegrees)

  /** returns the number of times the other angle will fit into this angle */
  override def /(x: Angle): BigDecimal = value / x.asDegrees

  override def toString: String = s"${Angle}(${value}°)"

  override def norm: Angle = {
    val normed = value % (360)
    if (normed < 0) {
      new DegreeAngle(normed + 360)
    } else {
      new DegreeAngle(normed)
    }
  }

  override protected def newObject(value: BigDecimal): Angle = degrees(value)
}
