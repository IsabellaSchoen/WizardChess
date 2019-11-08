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
    b1
  }

}
