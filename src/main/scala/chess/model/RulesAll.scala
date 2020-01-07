package chess.model


object RulesAll {

  /**
    * Kontrolle, ob Zelle richtig initialisiert ist
    */
  var start: Cell = Cell(-1, -1)

  /**
    * Kontrolle, ob move erlaubt bzw. Anfangsbedingungen für die Regeln der Figuren
    */
  def valid(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    start = board.Matrix(x1)(y1)

    //es muss eine Figur an der Startposition stehen
    if (start.isEmpty)
      return false

    start.figure.rule(board, x1, y1, x2, y2)
  }

  /**
    * Methode für das Werfen der Pawn-Figur
    * die Figur starte an einem Punkt, welcher definiert wird
    * es muss eine Figur am Anfang stehen
    * Abfrage, ob Figur ein Pawn ist; wenn ja, dann hit Methode; wenn nein, dann Regeln für die anderen Figuren
    */
  def hit(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    start = board.Matrix(x1)(y1)

    if (start.isEmpty)
      return false

    if (!board.Matrix(x1)(y1).figure.isInstanceOf[Pawn])
      start.figure.rule(board, x1, y1, x2, y2)
    else
      start.figure.asInstanceOf[Pawn].hit(x1, y1, x2, y2)
  }

}

