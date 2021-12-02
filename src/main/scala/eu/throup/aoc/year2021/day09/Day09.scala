package eu.throup.aoc.year2021.day09

import eu.throup.aoc.DayXX

object Day09 extends DayXX {
  override def part1(input: String) = {
    val grid: Grid = Grid(input)

    grid
      .values(grid.lowPoints)
      .map(_ + 1)
      .sum
  }

  override def part2(input: String) =
    Grid(input).basins.toSeq
      .map(_.size)
      .sorted
      .takeRight(3)
      .product
}
