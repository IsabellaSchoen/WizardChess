//Kreieren des Boards
//Buchstaben und Zahlen

package chess.model

import chess.model.{Board,Cell,Figure}

class BoardCreator(size:Int) {

  val b1 = Board(8)
  for (y <- 0 to 7) {
    for (x <- 0 to 7) {
      b1.Matrix(x)(y) = Cell(x, y)
    }
  }

}
