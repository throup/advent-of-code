package eu.throup.aoc.year2020.day17

import eu.throup.aoc.DayXX

// TODO: If you ever get bored, this day needs some refactoring...
object Day17 extends DayXX {
  override def part1(input: String): Long =
    DimensionalSpace
      ._3d(input)
      .rollToStage(6)
      .activeCubes

  override def part2(input: String): Long =
    DimensionalSpace
      ._4d(input)
      .rollToStage(6)
      .activeCubes
}
