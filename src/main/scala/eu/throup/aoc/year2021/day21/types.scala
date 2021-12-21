package eu.throup.aoc.year2021.day21

/** Position: Int, Score: Int
 */
type PlayerState = (Int, Int)

/** Player 1: SingleState, Player 2: SingleState, Round: Int
 */
type DualState = (PlayerState, PlayerState, Int)
