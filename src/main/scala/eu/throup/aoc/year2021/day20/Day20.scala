package eu.throup.aoc.year2021.day20

import eu.throup.aoc.DayXX
import eu.throup.measures.{DiagonalGrid, Point, PointRange, PointSet}

object Day20 extends DayXX {
  override def part1(input: String) = {
    val (algorithm, imageData) = parseInput(input)
    val image = DiagonalGrid(imageData)
    enhanceItXTimes(algorithm, image, 2).count(_ == '#')
  }

  override def part2(input: String) = {
    val (algorithm, imageData) = parseInput(input)
    val image = DiagonalGrid(imageData)
    enhanceItXTimes(algorithm, image, 50).count(_ == '#')
  }

  def enhanceItXTimes(
      algorithm: String,
      image: DiagonalGrid[Char],
      x: Int
  ): DiagonalGrid[Char] =
    if (x == 0) image
    else if (x == 1) enhanceImage(algorithm, image)
    else enhanceItXTimes(algorithm, enhanceImageTwice(algorithm, image), x - 2)

  /** This function exists to handle the scenario when the algorithm maps
    * the centre of:
    *      ...
    *      ...
    *      ...
    * to an X.
    *
    * This becomes problematic for an infinite image because virtually
    * _every_ pixel gets turned enabled. Luckily for us, they almost all
    * get disabled in the next turn.
    *
    * To compensate for that, this function crops the output image to 2
    * pixels wider than the input image.
    */
  def enhanceImageTwice(algorithm: String, image: DiagonalGrid[Char]) =
    enhanceImage(
      algorithm,
      enhanceImage(
        algorithm,
        image
      )
    ).crop(image.minX - 2, image.maxX + 2, image.minY - 2, image.maxY + 2)

  def enhanceImage(algorithm: String, image: DiagonalGrid[Char]) =
    DiagonalGrid(
      expandPoints(image)
        .map(p => p -> replacementPixel(algorithm, image, p))
        .toMap
    )

  def expandPoints(image: DiagonalGrid[Char]): PointSet =
    PointRange(
      image.minPoint - Point(3, 3),
      image.maxPoint + Point(3, 3)
    ).toSet

  def replacementPixel(
      algorithm: String,
      image: DiagonalGrid[Char],
      point: Point
  ): Char = {
    val frame = frameAround(image, point)
    val valFromBin: Int =
      frame
        .map {
          case '.' => 0
          case '#' => 1
        }
        .mkString
        .bin
        .toInt
    algorithm.charAt(valFromBin)
  }

  private def frameAround(image: DiagonalGrid[Char], p: Point) =
    image.valuesOrElse(
      Seq(
        p + Point(-1, -1),
        p + Point(0, -1),
        p + Point(1, -1),
        p + Point(-1, 0),
        p,
        p + Point(1, 0),
        p + Point(-1, 1),
        p + Point(0, 1),
        p + Point(1, 1)
      ),
      '.'
    )

  def parseInput(input: String): (String, Seq[Seq[Char]]) = {
    val sections = input.trim.split("\n\n")
    val algorithm = sections(0)
    val image = sections(1).split('\n').map(_.toCharArray.toSeq)
    (algorithm, image)
  }
}
