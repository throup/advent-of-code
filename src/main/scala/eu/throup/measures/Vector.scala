package eu.throup.measures

case class Vector private (x: BigDecimal, y: BigDecimal, length: BigDecimal, angle: Angle) {
  def unary_+ : Vector = this
  def unary_- : Vector = new Vector(-x, -y, length, Angle.HalfTurn - angle)

  def +(other: Vector): Vector = Vector(x + other.x, y + other.y)
  def -(other: Vector): Vector = Vector(x - other.x, y - other.y)

  def +(other: Angle): Vector = Vector(length, angle + other)
  def -(other: Angle): Vector = Vector(length, angle - other)

  def +(other: BigDecimal): Vector = Vector(length + other, angle)
  def -(other: BigDecimal): Vector = Vector(length - other, angle)

  def *(mult: Byte): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: Short): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: Char): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: Int): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: Long): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: Float): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: Double): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: BigInt): Vector = Vector(length * BigDecimal(mult), angle)
  def *(mult: BigDecimal): Vector = Vector(length * mult, angle)

  def /(mult: Byte): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: Short): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: Char): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: Int): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: Long): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: Float): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: Double): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: BigInt): Vector = Vector(length / BigDecimal(mult), angle)
  def /(mult: BigDecimal): Vector = Vector(length / mult, angle)
}

object Vector {
  def apply(length: BigDecimal, angle: Angle): Vector =
    Vector(angle.cos * length, angle.sin * length, length, angle)
  def apply(x: BigDecimal, y: BigDecimal): Vector =
    Vector(x, y, math.sqrt((x * x + y * y).toDouble), Angle.atan(x, y))
}
