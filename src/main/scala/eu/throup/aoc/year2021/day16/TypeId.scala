package eu.throup.aoc.year2021.day16

enum TypeId(val operator: Operator) {
  case Sum extends TypeId(Operator.Sum)
  case Product extends TypeId(Operator.Product)
  case Minimum extends TypeId(Operator.Minimum)
  case Maximum extends TypeId(Operator.Maximum)
  case Literal extends TypeId(Operator.Zero) // A "null" operator
  case Greater extends TypeId(Operator.Greater)
  case Less extends TypeId(Operator.Less)
  case Equal extends TypeId(Operator.Equal)
}
