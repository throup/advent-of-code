package eu.throup.aoc.year2021.day20

extension(string: String) {
  def bin: Long = java.lang.Long.parseLong(string, 2)
  def oct: Long = java.lang.Long.parseLong(string, 8)
  def hex: Long = java.lang.Long.parseLong(string, 16)
}

extension(char: Char) {
  def bin: Long = char.toString.bin
  def oct: Long = char.toString.oct
  def hex: Long = char.toString.hex
}
