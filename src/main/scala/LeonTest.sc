// Prototypes for classes

import model.{Cell, Figure}

/*class figure(id: Int) {
  val name = id match {
    case 1 => "pawn"
    case 2 => "knight"
    case 3 => "bishop"
    case 4 => "rook"
    case 5 => "queen"
    case 6 => "king"
    case _ => null
  }
}*/

val testPawn = new Figure("test")
val nullTest = new Figure("none")

println(testPawn.name)
println(nullTest.name)

class cell(x: Char, y: Int) {
  var figure = null.asInstanceOf[Figure]
  val posX = x
  val posY = y

  override def toString: String = {
    posX.toString + posY.toString
  }
}

val cell_1 = new cell('A', 1)

cell_1.figure = testPawn

println(cell_1.figure.name)

println(cell_1.toString())

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

b1.matrix.foreach(a => a.foreach(c => println(c.toString())))

println(b1.matrix(4)(6)) // E7

// initialization

// rooks
b1.matrix(0)(0).figure = new Figure("rook")
b1.matrix(0)(7).figure = new Figure("rook")
b1.matrix(7)(0).figure = new Figure("rook")
b1.matrix(7)(7).figure = new Figure("rook")

// knights
b1.matrix(1)(0).figure = new Figure("knight")
b1.matrix(6)(0).figure = new Figure("knight")
b1.matrix(1)(7).figure = new Figure("knight")
b1.matrix(6)(7).figure = new Figure("knight")

// bishops
b1.matrix(2)(0).figure = new Figure("bishop")
b1.matrix(5)(0).figure = new Figure("bishop")
b1.matrix(2)(7).figure = new Figure("bishop")
b1.matrix(5)(7).figure = new Figure("bishop")

// queens
b1.matrix(3)(0).figure = new Figure("queen")
b1.matrix(3)(7).figure = new Figure("queen")

// kings
b1.matrix(4)(0).figure = new Figure("king")
b1.matrix(4)(7).figure = new Figure("king")

// pawns
b1.matrix.foreach(a => a.foreach(c => { if (c.posY.equals(2) || c.posY.equals(7)) {
  c.figure = new Figure("pawn")
}}))

b1.matrix.foreach(a => a.foreach(c => if (c.figure != null) {
  println(c.figure.name + " " + c)
}))