package eu.throup.aoc.year2021.day12

import eu.throup.aoc.DayXX

object Day12 extends DayXX {
  override def part1(input: String) =
    CaveSystem(input)
      .findRoutes(PathValidator.OnlyOnceToSmallCaves)
      .size

  override def part2(input: String) =
    CaveSystem(input)
      .findRoutes(PathValidator.OneRepeatToSingleSmallCave)
      .size
}
