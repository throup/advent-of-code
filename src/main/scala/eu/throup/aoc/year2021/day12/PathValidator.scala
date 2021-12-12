package eu.throup.aoc.year2021.day12

import eu.throup.aoc.year2021.day12.Cave.{Big, Small, Start}

type PathValidator = (Set[Path], Route) => Set[Route]

object PathValidator {
  def apply(rule: (Cave, Route) => Boolean): PathValidator =
    (paths, route) =>
      paths
        .filter(path => rule(path.b, route))
        .map(route :+ _)

  val OnlyOnceToSmallCaves: PathValidator =
    PathValidator { (cave, route) =>
      cave match {
        case Big(_) => true
        case _      => !route.caves.contains(cave)
      }
    }

  val OneRepeatToSingleSmallCave: PathValidator =
    PathValidator { (cave, route) =>
      val multipleVisits =
        route.caves
          .groupBy(identity)
          .collect {
            case (Small(_), s) => s.size
          }
          .filter(_ > 1)

      val limit = if (multipleVisits.isEmpty) 2 else 1

      cave match {
        case Big(_) => true
        case Start  => false
        case _      => route.caves.count(_ == cave) < limit
      }
    }
}
