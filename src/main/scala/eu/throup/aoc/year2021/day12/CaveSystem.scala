package eu.throup.aoc.year2021.day12

import eu.throup.aoc.year2021.day12.Cave.{End, Start}

import scala.annotation.tailrec

case class CaveSystem(theMap: Map[Cave, Set[Path]]) {
  def findRoutes(validator: PathValidator) =
    untilAllTerminal(
      pathsFrom(Start).map(Route(_)),
      validator
    )

  @tailrec
  private def untilAllTerminal(
      routes: Set[Route],
      validator: PathValidator
  ): Set[Route] =
    if (routes.forall(_.end == End)) routes
    else
      untilAllTerminal(
        routes
          .flatMap(
            addOnePath(_, validator)
          ),
        validator
      )

  def addOnePath(route: Route, validator: PathValidator) =
    if (route.end == End) Set(route)
    else validator(pathsFrom(route.end), route)

  def pathsFrom(cave: Cave): Set[Path] =
    theMap
      .get(cave)
      .getOrElse(Set())
}

object CaveSystem {
  def apply(input: String): CaveSystem = {
    val paths: Set[Path] = parseInput(input)
    val value = (paths ++ paths.map(_.reverse))
      .filter(_.b != Start)
      .filter(_.a != End)
      .groupBy(_.a)
    CaveSystem(value)
  }

  private def parseInput(input: String): Set[Path] =
    input.trim
      .split("\n")
      .map(
        _.split("-")
          .map(Cave(_))
      )
      .map(path => Path(path(0), path(1)))
      .toSet
}
