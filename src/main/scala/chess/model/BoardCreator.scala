//Kreieren des Boards
//Buchstaben und Zahlen

package chess.model

import chess.model.{Board, Cell, Figure}

class BoardCreator {
  def create(size: Int): Board = {
    val b1 = Board(size)
    for (y <- 0 to 7) {
      for (x <- 0 to 7) {
        b1.Matrix(x)(y) = Cell(x, y)
      }
    }
    init(b1)
  }


    def init(b1: Board): Board = {
      // initialization

      // rooks
      b1.Matrix(0)(0) = b1.Matrix(0)(0).set("rook")
      b1.Matrix(0)(7) = b1.Matrix(0)(7).set("rook")
      b1.Matrix(7)(0) = b1.Matrix(7)(0).set("rook")
      b1.Matrix(7)(7) = b1.Matrix(7)(7).set("rook")

      // knights
      b1.Matrix(1)(0) = b1.Matrix(1)(0).set("knight")
      b1.Matrix(6)(0) = b1.Matrix(6)(0).set("knight")
      b1.Matrix(1)(7) = b1.Matrix(1)(7).set("knight")
      b1.Matrix(6)(7) = b1.Matrix(6)(7).set("knight")

      // bishops
      b1.Matrix(6)(7) = b1.Matrix(2)(0).set("bishop")
      b1.Matrix(5)(0) = b1.Matrix(5)(0).set("bishop")
      b1.Matrix(2)(7) = b1.Matrix(2)(7).set("bishop")
      b1.Matrix(5)(7) = b1.Matrix(5)(7).set("bishop")

      // queens
      b1.Matrix(3)(0) = b1.Matrix(3)(0).set("queen")
      b1.Matrix(3)(7) = b1.Matrix(3)(7).set("queen")

      // kings
      b1.Matrix(4)(0) = b1.Matrix(4)(0).set("king")
      b1.Matrix(4)(7) = b1.Matrix(4)(7).set("king")

      // pawns
      b1.Matrix.foreach(a => a.foreach(c => {
        if (c.x.equals(2) || c.y.equals(7)) {
          b1.Matrix(c.x)(c.y) = c.set("pawn")
        }
      }))
      b1
    }


  }
