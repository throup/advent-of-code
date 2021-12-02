package eu.throup.advent2020.day24

import org.scalatest.freespec.AnyFreeSpec

class day24Spec extends AnyFreeSpec {
  "day 24 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns a Long as output" in {
        val output: Long = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == 10)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 289)
      }
    }
  }

  "day 24 - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInput)
      }
      "returns a Long as output" in {
        val output: Long = part2(exampleInput)
      }
    }

    "examples" - {
      "Instruction example 1" in {
        val input = exampleInput
        val output = part2(input)

        assert(output == 2208)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 3551)
      }
    }
  }
  "findCoordinates" - {
    "single moves" - {
      "just east" in {
        assert(findCoordinates(Array("e")) == (1, 0))
      }
      "just west" in {
        assert(findCoordinates(Array("w")) == (-1, 0))
      }
      "just north east" in {
        assert(findCoordinates(Array("ne")) == (1, 1))
      }
      "just north west" in {
        assert(findCoordinates(Array("nw")) == (0, 1))
      }
      "just south east" in {
        assert(findCoordinates(Array("se")) == (0, -1))
      }
      "just south west" in {
        assert(findCoordinates(Array("sw")) == (-1, -1))
      }
    }
    "multiple moves in one direction" - {
      "just east" in {
        assert(findCoordinates(Array("e", "e")) == (2, 0))
      }
      "just west" in {
        assert(findCoordinates(Array("w", "w", "w")) == (-3, 0))
      }
      "just north east" in {
        assert(findCoordinates(Array("ne", "ne", "ne", "ne")) == (4, 4))
      }
      "just north west" in {
        assert(findCoordinates(Array("nw", "nw", "nw", "nw", "nw")) == (0, 5))
      }
      "just south east" in {
        assert(findCoordinates(Array("se", "se", "se", "se", "se", "se")) == (0, -6))
      }
      "just south west" in {
        assert(findCoordinates(Array("sw", "sw", "sw", "sw", "sw", "sw", "sw")) == (-7, -7))
      }
    }
    "multiple moves returning home" - {
      "east then west" in {
        assert(findCoordinates(Array("e", "w")) == (0, 0))
      }
      "south east then north west" in {
        assert(findCoordinates(Array("se", "nw")) == (0, 0))
      }
      "north east then south west" in {
        assert(findCoordinates(Array("ne", "sw")) == (0, 0))
      }
      "south west then north east" in {
        assert(findCoordinates(Array("sw", "ne")) == (0, 0))
      }
      "north west then south east" in {
        assert(findCoordinates(Array("nw", "se")) == (0, 0))
      }
      "clockwise circle" in {
        assert(findCoordinates(Array("w", "w", "nw", "nw", "ne", "ne", "e", "e", "se", "se", "sw", "sw")) == (0, 0))
      }
      "anti-clockwise circle" in {
        assert(findCoordinates(Array("w", "w", "sw", "sw", "se", "se", "e", "e", "ne", "ne", "nw", "nw")) == (0, 0))
      }
    }
  }

  def exampleInput = "sesenwnenenewseeswwswswwnenewsewsw\nneeenesenwnwwswnenewnwwsewnenwseswesw\nseswneswswsenwwnwse\nnwnwneseeswswnenewneswwnewseswneseene\nswweswneswnenwsewnwneneseenw\neesenwseswswnenwswnwnwsewwnwsene\nsewnenenenesenwsewnenwwwse\nwenwwweseeeweswwwnwwe\nwsweesenenewnwwnwsenewsenwwsesesenwne\nneeswseenwwswnwswswnw\nnenwswwsewswnenenewsenwsenwnesesenew\nenewnwewneswsewnwswenweswnenwsenwsw\nsweneswneswneneenwnewenewwneswswnese\nswwesenesewenwneswnwwneseswwne\nenesenwswwswneneswsenwnewswseenwsese\nwnwnesenesenenwwnenwsewesewsesesew\nnenewswnwewswnenesenwnesewesw\neneswnwswnwsenenwnwnwwseeswneewsenese\nneswnwewnwnwseenwseesewsenwsweewe\nwseweeenwnesenwwwswnew"

  def challengeInput = "sweswneswswswswwswswswseneswswnwswwne\nswwswswswnwswwnwswswswwswswseenwsesw\neeeeseeeneeenesweweeeswee\nswswswwneseneswswwnwswneswswwenewne\nseesesesenewsenwseseesesesesesenwesee\nnwsenwnwnewnewnwenenewnenenwneenenwne\nseseswsesenwnwswsenwneswswsesenwseswswsesw\nenwnwneswnewneswnwnenenwnesewsenenenwne\nswswnwsweseseswswswseweswswswnwseswsesw\nwwwweneswswwswwswswwwwswnewnww\nwwwnewwswswseswewwsewswwwswwnwne\nwswswwnwswwswwseewwweswwnesww\nwnwnwsenenwnwnwsenwnw\nnwwwsesenwnewsenenweneneeeswwsese\nsenenwnwwnwsewwewswnwnwewnwnwwnwnw\nseswsesenwseswseseeseeseseewswneseww\newswseneswseswswswnewnwwswswswnwewnw\nnwnwnwnenwsesesenwnwwewnwnwwewwe\nswswswswswswnwseneswwswsww\nswseneweswswseswseeseswswswseswwnww\nswseswswswneswswswewwwswwsw\nwswwnwnewnwnwwnwswwnesenwnwwwnww\nwwnewnewnwswwwwwsewwwswwwswese\nwswsesewswswwwwenewwwwwwnwswsw\nseeeeneweeesweeeeneeeeeew\nswseswneneseeswweswneswsewnwswsewese\neseseeneswsenweswesewenwseneenww\nneswnwnwnenwnwenenwwswnwswnenwnenwenw\nswewswswswswswwenwswswwswswnewswnesw\neswwnwnwnewsenwwnewwwsweesesene\nswneneswnwneneswnwwnenwneneneneeseswwsene\nswnwnenenenwnewenwnenweeneswneswnenwswsw\nseneewesesesesenwseswsesesesewseseesese\nwseswsenwwnesenwnwenwneesewnwwnwwnw\nnwnenenwnenwnwnenenwnwneeneesesweneswnwsw\nnesenewneneneneneneenenenenenenewe\nseneseseseswswseesenwsee\nswsweswenwswneswwswswwswswewsweswsw\newneswewwwnwwsewwswwwwwwwww\nwwwneswnwnweseseewnenwewwswww\nswneenwnwnwnwwwwnwsenwwwenwswnwnwww\nseenwswseseesenwsenwseseseseseseswsese\nseeesweeseeneesee\nnwnwwsenewnwwnwnwwsenwnewsesenwsenwnw\nwswswseswseneeswswswswseswswseswswnw\nneeseesesewsesewseeseseeeeeenwse\nswnwnwenewnwnwnwnwnwnwnwsenwnwnwnwnwnwe\nwwenweswnweenwnwsewswneenewsenenw\nnwseeweswseeswnwneseweenesenwenee\nnwnwnwnwnwnwnwnwnwnwswsenewesenwnwnwnw\nnwwneseneeneneneneneesenwnenene\nneneeneeseneneneswneswnwnenenenenewnene\nneneeswenwseneene\nnesenenweswswswsesenwnesesewswswwsww\nwwwsewnwnwwwnwnwneewse\nwwwnwnwneswsenwewswswewnewswnwnw\nneewwewneneeeseseneeneenewseneee\nwswnweswnewswwswsewnenewwe\nneneesesenwnwsweene\nswnwenenwnwnwseswswneewnwnenwnesewesesw\nseseswseswnwswswweseswseweswsesenwsee\nswsesesenwwnwenwnwneeswseswseswesesenw\nnwnwnwnwnwnwnwnwnwnenwsenwnw\nsenweeewewsewewnwesenewneswenwsw\nnenwnenwwweswsenwseseswswswwnenewswenw\neseeesweenweeesesesewseeeenwe\nneswnenenenweeswseeseswewswnwneneene\nsewnwwswwwnwnwnenenwswenwwwnwnwnw\nwseneseeeseseseseseseee\nseeeeeseweseseseseewwsesesesesew\nswseswnwswnwnesewsweswswsw\nneseneneeneenenewseeenesweenwene\nswseswsenewswseseseswseseseswse\nnwnwnwwswwswwwnene\nnwnwewnweneneenenwwwsenwswneswee\nwnwswnwneswnwwnwwnewwenwwwsenwww\nswneswneswswneenenenenwenweneneeew\nwwswwswswswwswwseswswswswnwswne\nswnwseesesesesesesenweseeseseseesewe\neenenwneneneswnenwneeneneeswnenenwneswne\nseeseseeswseewnweenwsenwswesesene\nwswswswswswseswsewwnwswswnww\neeeweneeswenenewweeeenenesenee\nnwwwewswnwwwwewnwsesew\nwswsenweenwnwwnwswewswnene\nseseesenweswswseseswsesweswwswnwswse\neeneswenenenwswnwnenenesw\nseseneswwnwneswswsweswnenwnwsenewswenesw\nneneneneneswneneswnenenenwneswnenenenee\nneeswnenenenenenenenwnewe\nswswswnwseswseswsenwswswswseswsesw\nnenwswswswswswswwsenwseswswwswswswswswesw\nwseesenwwnwswseseenwseseseswseeswsenese\nsewwnenewwwwwwswwwwwnww\nwsewseseseneeeseeweeesew\nnwnwnwnwenwnwnwnwnwnwnwenwnwnwswsenwswnw\nneswwseswseswseswswswseseswnewseswswne\nwswnwwnwenwwnwwnwwewwwww\nnwnwnwswwwwnwewnewwwnwnw\neweeeseseeeeseee\nwswwnwnwnwnwenwnwnwswnwnwwwnenw\nnenenwnenenenenenenenesenenenenw\nwneneswneseeneenenenenweneeeneene\nwewnwenwwenwnwnwnesenwnwwnwnwew\nseseswswneseseseseseseseneswsesese\nneneneneneenwsewnesenenenenenwnwnewnene\nwwwwwnewswseswwsewnwwwwneww\nswwswwwwwwesewswwswwwswnene\nnwenwnenwneneswnwnenesewnwnenenwnwnwne\nseenwswswseseeswswnwenwenwnweesene\nsenwsewnenwneswwnwnwenwswenwwnwwnenw\nnewsenwseweesenewnwnwnwwwswnwnwnwsw\neseseseeewnwesewswseenwenwewne\nwswneswswswswswswsw\nsewswsesesesesesewswwseneseenesw\nenenwnwsenwnwwsenwnwnwwwnwnwnwsewnw\nsenwewswsenenwwseesweweneeseswnew\nsenenenwnenenwnenwnwnenwenenwnwnwswwe\nneeseseeseseeewseeseseee\nwswwwnwsewnewswswnewwwse\nnwnwnenwwwwswwsewwwewsewswswswesw\nesweneeeneeeeeeenwne\nswwwnewswnwwswwsenweseeewswswswnw\nnwnwnwsesenwnwwwenenesewnwswwnwnwnwsw\nnwswnenenenwnwnwenenenwswnweewnwnwne\nnwnenwwnwnwewnwnwnwsenwnwsewnwnwsene\neswnwswswswsweswwswwsweswswswswwnwsw\nenewnenenenenewneneeswnenwnenwneew\nswswseswswneswewswwseswnwwnwneswneswsesw\nnwewswseeswsenwwweswneesewenenwe\neswswseswseseswswnewse\nnwneneneseneneneseneneneneneswnenenwnene\nsesesenwenweeenwnwswwsweeneneenene\nnwwnwnwsesenwnwnwnwnwnenenwwneswesenwne\nseewnenwwnwnewwsenwswwwwwnwnew\neswneneeneenwneswneneneneswnewsenee\nneneswnenwnenwnwnenene\neesweseeeeneeweeeeeenwenw\nseseseseneseseseseswswnewnwsesewswswse\nnwnwsenwswwnwnesenwenenenwneeswnwnwne\nwneswwwwnwnewnwwswswwnwwnwwsene\neswneneswnweneneneneneeneene\nnwnwwwsenwswswnwswenenwnew\nswswswswwwnwwenwwswswswneswswseswe\newwnwseseswnewswwneswwswnwswswee\neesewwneeeseneeseseweseeseee\neeweweeeeesenweeeeeeeesw\nenwnenwnwnenwnwnwnenwswneswnwnwnwenwnw\nnwnwwwswsewwnwnweswnwwnewswnewnwnw\neeneneewseneneeenenweeeweeseese\nnwnwswsenewwwsewnwwwneww\nnewnenenwnwwnweseswsenenesenwnenew\neseseneseeeseseswwnwseeeenwseesese\nseswswswnewnwnwewswsenweswseenwswse\nswsenenwseswseseswwseneswsewswsenenew\nwnenenwnenwnwewnwnenwseneenwnwnewnw\nneneneenwwseeenwswseeewseneeewse\nswewsesenwneseneeswenwsese\neswseesweeenenenweenenenewnenwne\nnewnenenenewnenesweneneneneenwnwnenesw\nwnwwnwwnwwnwnwsewwnwnwnww\nsewwwsewwwwwwnenwenenwwww\neseewnesenwseseseseneswsenwsesesenenww\nneswswnewseswseeswswswsewswseseswswswe\nseswwnwsweweswnwnesenwswseeenwsesw\nnenenenenenenweneewwenesweswenenw\nseseneseswsesewnwwseesenwnwsw\nnwswnwweswnenwnwnenenenwnwnwswnenwnwnwnw\nnwswnwewswswswwewwwswswswwswnewsw\nseneseneeeeswswsenwnewnwwenenwwswne\nseeseseseenwswsewseswwneswsenewnwswne\nwwwwnwwewewswswwwwwwewnw\nneeswewwwwwwswwwseenwnewwseew\nneneswnwwnenwnwwswwnwnwesese\neeneneeneneneneneneewee\nnwwswseneeeenewneeeeneenewsweesw\nnweswsweswneswenwsweeneswswwswwnw\nswneneneseneeeswnwswnweneneneenwneee\nseenwswnwnwswswswswseswswesweswseswsene\nswswswneswswnwenwsenesewenwnwsenwswswswe\neeneweeseseweeseeesesenesesese\nnwnesenenwnwnenwnenwnewswneenenenenene\nwnewnenwswwnwwsenwseseeswnenewewwsw\neeeseneweenenwwweewsweneeee\nneneswenwswnwenesweneneeeneneeneeene\neneswweeswesenwnenenenee\nnwswweswneesesenenwswswswe\nneswseswseneswwneneneeswnwnenenewnenene\nswswesesenwsenewseseseseswseeswswswswse\neswnwswswswseswswswswswsw\nnwnwnwewnwnwneneenwwsenwnwnwnwwnene\nenewswwseseswnenwneenenwnwsesesewnenene\neenwnwwswenwnwnwwenesenwewneswne\nseseseseswseneseseneewswsesewsese\nneneneneneneswswnewneenwneneewnenenee\newswwswswwnwnwwwswswswseswwwwse\nwnwneswwswswnwwnwweenenwnwewsenwne\nsewneseenwseseseneenwswseseesew\nenweeneseswswneeneweeswsesweenw\nnwnwnwsenwnwnwnenwnwnwwnwnwnwnwsew\nnwnenwwnwnwswnwnwwnwnwwwnwenwenww\nnwnenenwneswswnwnwnenwnesenwsenenenenenenw\nsesenesewnwseseeseew\nswnwwnwsenwneswnwneenenwsenesene\nseeseswwnewwnwewwwswesenwnewsenw\nswsweswswweswwswewswswnwswwneswswsw\nseeseswnwweseenesesesenesesenwwseew\nswseswswswswsweswswsewswswenwswnesww\nnwnwwwnwwnwswnwnwseenwenwsenwnwnenw\nnwsewsewnwsesesenweswseseseseeesese\nseeneeenwneneneeeenenenee\nswnwwnwnwnwnwnwnenwnwnwnwnw\nswseeswswseneeseswseeswsesenwwswswswnw\nwnewwswsewwwwww\nnwseeswnwnweewweswnewswnwweee\nswseswsesenesesenwwseswseeswseseseenwse\nswswnwwseswneeswwswneseswnew\nseseeseneseseweeseesesenenwseswww\nnesenwwewsesweesewseswswwswnwne\nswnwwneeenwnwnwswswenwnwnenwenwwswnw\nsenwnwsenwwsenwnwne\nswswswnwswwseneeneswwswnewneswnesewswse\nwsenenweewwewnwwnwnenwnwnwesenw\nnwnwnwnwwnwnwnwnwnwsenwswnwnwnwnwnwsene\nneenenwneneseneesweneswwswne\neweeeesenweeeenwseweweeee\nwswwweswsewwnewseswwwnewwwnesw\nwnwnenwswnwnwnwnwwwnwwnwnw\nnwnwewnwswneenenwnenwnwnwnenw\neneeweseeswswesenwnwseeswenwseene\neswnwnwsweeeswwswnwswwswwseenww\nweswsesewnwwseeseweseseeeneenenew\nnwnwnwnwnwwewnwnwnwwnwnwsenwnw\nnwnwnwswnwnwnwnenewnwenwnwne\nnwseneewneseneeseseseswsewwsesewsew\nswsenweseseeeseswseeeseseewesenwne\nwswnwnwwwewswnwwnewsenwnwweww\nseesewswnwesenwswswsenwswswseswesw\nneesewnwsenwsewnwsweswsenenewneewse\nnwnwwwnwnwsewnewwwnwnwwnwnwswenw\nneneswswswswsesenwswswswswwswseseswswsw\nnweeswswneneeeneneewswneenenenwswne\nwseeeweseeeeseneeeeenweeew\nnenenewneneneneswneneseneeneeenenewne\nnwnwnwenwnwnwnwnwnenwswnw\nsewsesesenenesewseseseseneeesesewe\neeneseswseweeeeeeweseeeenwe\nnwnwnwnwewnwwswnwnwnwwenwenwnwenw\nnwnwnwsewseeseenww\nnwenwnenenwswnwenenwneswneswnwnwnwnwnwne\nwwswseneswseneweswenwseseswswsweswsw\nswwwnweswswwwnesewsw\neneeeeeeswwneneeeee\nnwnewwwwwseewswnewwwwwwnww\nsenwnwnwnwnwnwnwnwwsenenwnwnwnwnwsenwnwnw\nseswnwsesewseswseeseseswewnwesenwswse\nnewneswneneeneenenenenenewne\nswwwswswswweeswnwnewseseneseneenesew\nwwewwnewswnwwwwnwnwswwsewwse\nnenenesenesenewsenenwnwnesenewnwnenenenw\nseeeenwesweweeeneneseeneseswsw\nnwnwswnwsenewnenwnwnwnwsenwnenwnwnwnwnenw\nswneneneneneenesewneeeseswewswnenee\nsweewenwneenesenwsweneneeneneswne\nsesweseseenwseseseswsenwseneseseseseese\nnwsewwenenwseewnwewwsweswewnwse\nseswswseseneesewwenwswwswneseeneswswse\nsweswsweseswswswsweswswswswswnwswswwnw\nnewnwseeenwnwenesewwneswswne\nnenwnenwwwneenenwnenwnenwswnwneseese\neseenwesesesenwseweseeeeswswnwe\nnwnwwnwnwwenwnwnwwnwnwwsw\nswwwseswwnwwswswenewwswswwwnwww\nwnenwnenenwnwwnenwsenwnwsenwnwnesewsene\nswnwwsewswnewwswwwewewwnwwsw\nwsweswswneneswseneswswnwswswenwswse\nswnwseseenewseswsenw\neesenesweeneesenwswseeseewnweee\neneeeeneeeneneewnesene\nseeseneseswwneweseneeeswenwsesenesw\nwswseswwswswwwneswneseswneeswwwwwsw\nnwesesweewswnwenwnweswnwswsw\nnwnwnwnenwnwsenwnesenwnwenwwnwnwwnwnw\nswneswenwnwnweneenwnwswnwnenwnwnwnenenwse\nnesweswnwswnwneneeneewnwneseneeswnenese\nneeenwseneewneswnenwseeeneenenenenew\nswnwnwenwnwnweswnwnwnwnwnwnwnenwnwnwnese\nnesenewswwneneswnweneenwnenee\nswswswswswneswswswswswswswswsw\nneneneneeneneewswenenenwewneswenene\nweneneseeneneeenenenweeeeneewswe\nwwwsenwwsewnwenenenwneswwnewseseesw\nnewnwnesenesenwneesewnwwnwwnwenenw\nseenwnwneeeneeeweenesweeesese\nnwnenewnesenwwnwnenwnwnwnwwnwseenwwnwse\newnenwswneseneeese\nwneewseeswneneswsweeeenwnesenwne\nswswseswnwsewsewnwswswwswnwswswnesesw\nseseseseseeseesesesewsese\nnwwswswwswsewwwswsww\nswneswseeneswsewswswwswsw\nneswwswswnewewnesewwesenesenesww\nnewwnenwneneseesenenenenenenenweswnw\nweswsweswswswwswwneswswswswswswnwwsw\nswswswswswneswenwswweswswseswseseswnw\nwsewnwsewnwwwsweneww\nseeeeeenweesweeseee\nsesesesesesesesesewesenwswneneseseswnw\nnwnenwnwnwsenwnwnwwnwnweneswwe\nwnenwnwsewnwwnewnwsesenwnwsenewnw\nseeseneswesewnwe\nswwnenwnwenenwseswneswwnesesweeswnwnw\nswwswneswswwwswwneseeswwswswswsenwsw\neewwwneeseneeewwewenenewne\nswswswwseneeneswwswseswswnewswnewnwsw\nnewnenenewswenesenenwneneesewneenesw\nneeseswnweswswsenwwsenwweesesesesesw\nseeseeswenwnweeeeeeeenw\nseswswswseswwseenwseseswseseseesesw\nwswswswnenwswswseswswseswswseswswnesesww\nesesenweeswseeeseenesenwese\neseseenwseseeseseweesewseseesesw\nenwnwnwsenwnwswnwnwnwnwnwnwnenwne\nesesewneneeseswseewwnweeseeswnwse\nwsewwenwswnwwsewwnwwwwswwwew\nnwswswswseneswswswneseseeswwwnesenesesw\nnwsesenwseeseesesweseseseeseeseeew\nenewwwswwewwewwnenwsewwwwse\nnwnweneeswewwnwnenwenwwwenenwnw\nwwwwwneswswswwwwwewwwswnew\neswseseswnwswsewsw\nwsweswwwenwwwsesewweswnwswwnwsw\nnwsewwwnwnwsenwnweewwnwwnenewnw"
}
