package chess.model

//Start- und Endpunkt
//boolean - erlaubter Zug
//rules kann die Figur sehen
//Position und Board übergeben

object RulesAll {
  var start: Cell = Cell(-1, -1)
  var end: Cell = Cell(-1, -1)

  //Kontrolle, ob move erlaubt:
  def valid(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    start = board.Matrix(x1)(y1)
    end = board.Matrix(x2)(y2)

    if (start.isEmpty)
      return false

    start.figure.get.rule(board, x1, y1, x2, y2)
    }
}

