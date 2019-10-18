// Prototypes for classes

class figure(id: Int) {
  val name = id match {
    case 1 => "pawn"
    case _ => null
  }
}

val testpawn = new figure(1)
val testnone = new figure(0)

println(testpawn.name)
println(testnone.name)

class cell(x: Char, y: Int) {
  var figure = null.asInstanceOf[figure]
  val posX = x
  val posY = y
}

val cell_1 = new cell('A', 1)

cell_1.figure = testpawn

println(cell_1.figure.name)

println(cell_1.posX.toString + cell_1.posY)

class board {
  val matrix = Array.ofDim[cell](8, 8)
}

val b1 = new board
for (y <- 0 to 7) {
  for (x <- 0 to 7) {
    val ch = x match {
      case 0 => 'A'
      case 1 => 'B'
      case 2 => 'C'
      case 3 => 'D'
      case 4 => 'E'
      case 5 => 'F'
      case 6 => 'G'
      case 7 => 'H'
    }

    b1.matrix(x)(y) = new cell(ch, y + 1)
  }
}

b1.matrix.foreach()