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

    if (start.isEmpty)
      return false

    //Regeln:

    //Bauern - pawn:

    if (start.figure.get.name.equals("pawn")) {
      if (start.figure.get.moved) {
        if (start.figure.get.color.equals('B')) {
          if (x1 == x2 && y2 == y1 + 1)
            return true
        } else if (start.figure.get.color.equals('W')) {
          if (x1 == x2 && y2 == y1 - 1)
            return true
        }
      } else {
        if (start.figure.get.color.equals('B')) {
          if (x1 == x2 && y2 == y1 + 2 || x1 == x2 && y2 == y1 + 1)
            return true
        } else if (start.figure.get.color.equals('W')) {
          if (x1 == x2 && y2 == y1 - 2 || x1 == x2 && y2 == y1 - 1)
            return true
        }
      }
      return false
    }
    true
  }
}