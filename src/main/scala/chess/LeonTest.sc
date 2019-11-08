// Prototypes for classes

import chess.model._

class figure(id: Int) {
  val name = id match {
    case 1 => "pawn"
    case 2 => "knight"
    case 3 => "bishop"
    case 4 => "rook"
    case 5 => "queen"
    case 6 => "king"
    case _ => null
  }
}

val testPawn = Figure("test")
val nullTest = Figure("none")

println(testPawn.name)
println(nullTest.name)

/*class cell(x: Char, y: Int) {
  var figure = null.asInstanceOf[Figure]
  val posX = x
  val posY = y

  override def toString: String = {
    posX.toString + posY.toString
  }
}*/

val cell_1 = Cell(0, 0)

cell_1.set(testPawn.name)

if (cell_1.figure.isDefined)
  println(cell_1.figure.get)

println(cell_1)

class board {
  val matrix = Array.ofDim[Cell](8, 8)
}

val b1 = new board
for (y <- 0 to 7) {
  for (x <- 0 to 7) {
    b1.matrix(x)(y) = Cell(x, y)
  }
}

b1.matrix.foreach(a => a.foreach(c => println(c)))

//println(b1.matrix(4)(6)) // E7

// initialization

// rooks
b1.matrix(0)(0) = b1.matrix(0)(0).set("rook")
b1.matrix(0)(7) = b1.matrix(0)(7).set("rook")
b1.matrix(7)(0) = b1.matrix(7)(0).set("rook")
b1.matrix(7)(7) = b1.matrix(7)(7).set("rook")

// knights
b1.matrix(1)(0) = b1.matrix(1)(0).set("knight")
b1.matrix(6)(0) = b1.matrix(6)(0).set("knight")
b1.matrix(1)(7) = b1.matrix(1)(7).set("knight")
b1.matrix(6)(7) = b1.matrix(6)(7).set("knight")

// bishops
b1.matrix(6)(7) = b1.matrix(2)(0).set("bishop")
b1.matrix(5)(0) = b1.matrix(5)(0).set("bishop")
b1.matrix(2)(7) = b1.matrix(2)(7).set("bishop")
b1.matrix(5)(7) = b1.matrix(5)(7).set("bishop")

// queens
b1.matrix(3)(0) = b1.matrix(3)(0).set("queen")
b1.matrix(3)(7) = b1.matrix(3)(7).set("queen")

// kings
b1.matrix(4)(0) = b1.matrix(4)(0).set("king")
b1.matrix(4)(7) = b1.matrix(4)(7).set("king")

val o = b1.matrix(4)(0)

print(b1.matrix(o.x)(o.y))


// pawns
b1.matrix.foreach(a => a.foreach(c => {
  if (c.x.equals(2) || c.y.equals(7)) {
    b1.matrix(c.x)(c.y) = c.set("pawn")
  }
}))

b1.matrix.foreach(a => a.foreach(c => if (c.figure.isDefined) {
  println(c)
}))