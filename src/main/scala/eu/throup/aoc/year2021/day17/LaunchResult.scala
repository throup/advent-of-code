package eu.throup.aoc.year2021.day17

import eu.throup.measures.Point

sealed trait LaunchResult {
  def velocity: Point
  def highest: Point
}
case class Hit(velocity: Point, highest: Point) extends LaunchResult
case class Overshoot(velocity: Point, highest: Point) extends LaunchResult
case class Undershoot(velocity: Point, highest: Point) extends LaunchResult
