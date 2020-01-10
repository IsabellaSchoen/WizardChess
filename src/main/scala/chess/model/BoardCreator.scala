//Kreieren des Boards
//Buchstaben und Zahlen

package chess.model

import chess.model.boardComponent.{BoardTrait, Board}

case class BoardCreator(size: Int) {
  def create: BoardTrait = {
    val b1 = Board(size)
    for (y <- 0 to 7) {
      for (x <- 0 to 7) {
        b1.Matrix(x)(y) = Cell(x, y)
      }
    }
    b1
  }

  def init(b1: BoardTrait): BoardTrait = {
    // initialization

      // rooks
      b1.Matrix(0)(0) = b1.Matrix(0)(0).set("rook")
      b1.Matrix(0)(7) = b1.Matrix(0)(7).set("rook", 'W')
      b1.Matrix(7)(0) = b1.Matrix(7)(0).set("rook")
      b1.Matrix(7)(7) = b1.Matrix(7)(7).set("rook", 'W')

      // horse
      b1.Matrix(1)(0) = b1.Matrix(1)(0).set("horse")
      b1.Matrix(6)(0) = b1.Matrix(6)(0).set("horse")
      b1.Matrix(1)(7) = b1.Matrix(1)(7).set("horse", 'W')
      b1.Matrix(6)(7) = b1.Matrix(6)(7).set("horse", 'W')

      // bishops
      b1.Matrix(5)(7) = b1.Matrix(5)(7).set("bishop", 'W')
      b1.Matrix(5)(0) = b1.Matrix(5)(0).set("bishop")
      b1.Matrix(2)(7) = b1.Matrix(2)(7).set("bishop", 'W')
      b1.Matrix(2)(0) = b1.Matrix(2)(0).set("bishop")

      // queens
      b1.Matrix(3)(0) = b1.Matrix(3)(0).set("queen")
      b1.Matrix(3)(7) = b1.Matrix(3)(7).set("queen", 'W')

      // kings
      b1.Matrix(4)(0) = b1.Matrix(4)(0).set("king")
      b1.Matrix(4)(7) = b1.Matrix(4)(7).set("king", 'W')

      // pawns
      b1.Matrix.foreach(a => a.foreach(c => {
        if (c.y.equals(1))
          b1.Matrix(c.x)(c.y) = c.set("pawn")
        else if (c.y.equals(6))
          b1.Matrix(c.x)(c.y) = c.set("pawn", 'W')
      }))
      b1
    }


}
