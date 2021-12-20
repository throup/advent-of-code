package eu.throup.measures

type PointSet = Set[Point]
extension(set: PointSet) {
  def xMin = set.map(_.x).min
  def xMax = set.map(_.x).max
  def yMin = set.map(_.y).min
  def yMax = set.map(_.y).max

  def xRange: Range = (xMin to xMax)
  def yRange: Range = (yMin to yMax)

  def toRange: PointRange = PointRange(xMin, xMax, yMin, yMax)
}
object PointSet {
  def empty = Set()
}