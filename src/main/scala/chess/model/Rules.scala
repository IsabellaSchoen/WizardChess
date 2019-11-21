package chess.model

//Start- und Endpunkt
//boolean - erlaubter Zug
//rules kann die Figur sehen
//Position und Board übergeben

object Rules {
  //Kontrolle, ob move erlaubt:
  def valid(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    val start: Cell = board.Matrix(x1)(y1)
    val end: Cell = board.Matrix(x2)(y2)
    //Regeln:

    //Bauern - pawn:

    if (start.figure.get.name.equals("pawn")) {
      if (start.figure.get.moved) {
        if (board.Matrix(x1)(y1).figure.get.color.equals('B')) {
          return x1 == x2 && y2 == y1 + 1
        } else if (board.Matrix(x1)(y1).figure.get.color.equals('W')) {
          return x1 == x2 && y2 == y1 - 1
        }
      } else {
        if (board.Matrix(x1)(y1).figure.get.color.equals('B')) {
          return x1 == x2 && y2 == y1 + 2
        } else if (board.Matrix(x1)(y1).figure.get.color.equals('W')) {
          return x1 == x2 && y2 == y1 - 2
        }
      }
    }
    true
  }
}