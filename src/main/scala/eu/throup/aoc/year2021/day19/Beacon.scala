package eu.throup.aoc.year2021.day19

case class Beacon(x: Int, y: Int, z: Int) {
  def +(o: Beacon) = Beacon(x + o.x, y + o.y, z + o.z)
  def -(o: Beacon) = Beacon(x - o.x, y - o.y, z - o.z)

  def distanceTo(other: Beacon): Int = {
    val dx = x - other.x
    val dy = y - other.y
    val dz = z - other.z
    Math.sqrt(dx * dx + dy * dy + dz * dz).toInt
  }

  def manhattanDistanceTo(other: Beacon): Long =
    Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z) + 3
}

object Beacon {
  def apply(input: String): Beacon = {
    val ints = input.split(',').map(_.toInt)
    Beacon(ints(0), ints(1), ints(2))
  }
}
