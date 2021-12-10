package eu.throup.aoc.year2020.day17

import eu.throup.aoc.DayXX

// TODO: If you ever get bored, this day needs some refactoring...
object Day17 extends DayXX {
  override def part1(input: String): Long =
    The3DSpace(input)
      .rollToStage(6)
      .activeCubes

  override def part2(input: String): Long =
    The4DSpace(input)
      .rollToStage(6)
      .activeCubes
}
