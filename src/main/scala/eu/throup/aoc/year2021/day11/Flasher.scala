package eu.throup.aoc.year2021.day11

case class Flasher(count: Int = 0) {
  def collectFlashes(grid: FlashingGrid): GridWithFlasher =
    (
      grid.map(i => if (i > 9) 0 else i),
      Flasher(count + grid.count(_ > 9))
    )
}
