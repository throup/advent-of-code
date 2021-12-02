package eu.throup.advent2020

package object day17 {
  def part1(input: String): Long = {
    val space = The3DSpace(input)
    space.rollToStage(6).activeCubes
  }

  // ---

  def part2(input: String): Long = {
    val space = The4DSpace(input)
    space.rollToStage(6).activeCubes
  }

  // ---
}