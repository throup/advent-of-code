package eu.throup.aoc.year2021.day11

import eu.throup.measures.{DiagonalGrid, Point}

type FlashingGrid = DiagonalGrid[Int]
type GridWithFlasher = (FlashingGrid, Flasher)

extension (grid: FlashingGrid) {
  def flash(p: Point): FlashingGrid =
    grid.map((o: Point, i: Int) =>
      if ((grid.adjacentPoints(p) + p).contains(o)) i + 1 else i
    )
}

object FlashingGrid {
  def apply(input: String): FlashingGrid = {
    FlashingGrid(
      input.trim
        .split("\n")
        .map(
          _.toCharArray
            .map(_ - 48) // converts numerical char to int
        )
    )
  }

  def apply(input: Array[Array[Int]]): FlashingGrid = {
    FlashingGrid(
      input.indices
        .flatMap(y => input.head.indices.map(x => Point(x, y) -> input(y)(x)))
        .toMap
    )
  }

  def apply(input: Map[Point, Int]): FlashingGrid = DiagonalGrid(input)
}
