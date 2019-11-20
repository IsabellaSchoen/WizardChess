package chess.model

//Start- und Endpunkt
//boolean - erlaubter Zug
//rules kann die Figur sehen
//Position und Board übergeben

case class Rules() {

  //Kontrolle, ob move erlaubt:
  def valid(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    //Regeln:

    //Bauern - pawn:

    if(board.Matrix(x1)(y1).figure.get.name.equals("pawn")){
      if(board.Matrix(x1)(y1).figure.get.color.equals('B')) {
        return x1 == x2 && y2 == y1 + 1
      } else if (board.Matrix(x1)(y1).figure.get.color.equals('W')) {
        return x1 == x2 && y2 == y1 - 1
      }
    }
    true
  }

}
