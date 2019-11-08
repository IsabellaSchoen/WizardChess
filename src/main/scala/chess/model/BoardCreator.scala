//Kreieren des Boards
//Buchstaben und Zahlen

package chess.model

import chess.model.{Board,Cell,Figure}

class BoardCreator(size:Int) {

  val b1 = Board(8)
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

      b1.Matrix(x)(y) = Cell(ch, y + 1)
    }
  }

}
