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

    val fig: String = start.figure.get.name
    val rule = fig match {
      case "pawn" => pawnRule(x1, y1, x2, y2)
      case "king" => kingRule(x1, y1, x2, y2)
      case "rook" => rookRule(x1, y1, x2, y2)
      case "horse" => horseRule(x1, y1, x2, y2)
      case "bishop" => bishopRule(x1, y1, x2, y2)
      case "queen" => queenRule(x1, y1, x2, y2)
    }

    return rule
  }

  def pawnRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    //Bauern - pawn:
    if (start.figure.get.name.equals ("pawn") ) {
    if (start.figure.get.moved) {
    if (start.figure.get.color.equals ('B') ) {
    if (x1 == x2 && y2 == y1 + 1)
    return true
  } else if (start.figure.get.color.equals ('W') ) {
    if (x1 == x2 && y2 == y1 - 1)
    return true
  }
  } else {
    if (start.figure.get.color.equals ('B') ) {
    if (x1 == x2 && y2 == y1 + 2 || x1 == x2 && y2 == y1 + 1)
    return true
  } else if (start.figure.get.color.equals ('W') ) {
    if (x1 == x2 && y2 == y1 - 2 || x1 == x2 && y2 == y1 - 1)
    return true
  }
  }
    return false
  }
  false
  }



    //König - king
  def kingRule (x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    if (start.figure.get.name.equals("king")) {
      if ((x2 == x1 + 1 && y1 == y2) ||
        (x1 == x2 && y2 == y1 - 1) ||
        (x2 == x1 - 1 && y1 == y2) ||
        (x1 == x2 && y2 == y1 + 1))
        return true
    }
    return false
  }



    //Turm - rook
  def rookRule (x1: Int, y1: Int, x2: Int, y2: Int) : Boolean = {
    if (start.figure.get.name.equals("rook")) {
      if ((x2 == x1 && y2 == y1 + 1) || (x2 == x1 && y2 == y1 + 2) || (x2 == x1 && y2 == y1 + 3) ||
        (x2 == x1 && y2 == y1 + 4) || (x2 == x1 && y2 == y1 + 5) || (x2 == x1 && y2 == y1 + 6) ||
        (x2 == x1 && y2 == y1 + 7) ||
        (x2 == x1 + 1 && y2 == y1) || (x2 == x1 + 2 && y2 == y1) || (x2 == x1 + 3 && y2 == y1) ||
        (x2 == x1 + 4 && y2 == y1) || (x2 == x1 + 5 && y2 == y1) || (x2 == x1 + 6 && y2 == y1) ||
        (x2 == x1 + 7 && y2 == y1) ||
        //Rückwärts
        (x2 == x1 && y2 == y1 - 1) || (x2 == x1 && y2 == y1 - 2) || (x2 == x1 && y2 == y1 - 3) ||
        (x2 == x1 && y2 == y1 - 4) || (x2 == x1 && y2 == y1 - 5) || (x2 == x1 && y2 == y1 - 6) ||
        (x2 == x1 && y2 == y1 - 7) ||
        (x2 == x1 - 1 && y2 == y1) || (x2 == x1 - 2 && y2 == y1) || (x2 == x1 - 3 && y2 == y1) ||
        (x2 == x1 - 4 && y2 == y1) || (x2 == x1 - 5 && y2 == y1) || (x2 == x1 - 6 && y2 == y1) ||
        (x2 == x1 - 7 && y2 == y1))
        return true
    }
    return false
  }


    //Pferd - horse
  def horseRule (x1: Int, y1: Int, x2: Int, y2: Int) : Boolean = {
    if (start.figure.get.name.equals("horse")) {
      if ((x2 == x1 - 2 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 2) || (x2 == x1 - 1 && y2 == y1 + 2) ||
        (x2 == x1 + 1 && y2 == y1 + 2) || (x2 == x1 + 2 && y2 == y1 + 1) ||
        //Rückwärts
        (x2 == x1 + 2 && y2 == y1 - 1) || (x2 == x1 + 1 && y2 == y1 - 2) || (x2 == x1 + 1 && y2 == y1 - 2) ||
        (x2 == x1 - 1 && y2 == y1 - 2) || (x2 == x1 - 2 && y2 == y1 - 1))
        return true
    }
    return false
  }



    //Läufer - bishop
  def bishopRule (x1: Int, y1: Int, x2: Int, y2: Int) : Boolean = {
    if (start.figure.get.name.equals("bishop")) {
      //nach rechts oben
      if ((x2 == x1 + 1 && y2 == y1 - 1) || (x2 == x1 + 2 && y2 == y1 - 2) || (x2 == x1 + 3 && y2 == y1 - 3) ||
        (x2 == x1 + 4 && y2 == y1 - 4) || (x2 == x1 + 5 && y2 == y1 - 5) || (x2 == x1 + 6 && y2 == y1 - 6) ||
        (x2 == x1 + 7 && y2 == y1 - 7) ||
        //nach links oben
        (x2 == x1 - 1 && y2 == y1 - 1) || (x2 == x1 - 2 && y2 == y1 - 2) || (x2 == x1 - 3 && y2 == y1 - 3) ||
        (x2 == x1 - 4 && y2 == y1 - 4) || (x2 == x1 - 5 && y2 == y1 - 5) || (x2 == x1 - 6 && y2 == y1 - 6) ||
        (x2 == x1 - 7 && y2 == y1 - 7) ||
        //nach rechts unten
        (x2 == x1 + 1 && y2 == y1 + 1) || (x2 == x1 + 2 && y2 == y1 + 2) || (x2 == x1 + 3 && y2 == y1 + 3) ||
        (x2 == x1 + 4 && y2 == y1 + 4) || (x2 == x1 + 5 && y2 == y1 + 5) || (x2 == x1 + 6 && y2 == y1 + 6) ||
        (x2 == x1 + 7 && y2 == y1 + 7) ||
        //nach links unten
        (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 2 && y2 == y1 + 2) || (x2 == x1 - 3 && y2 == y1 + 3) ||
        (x2 == x1 - 4 && y2 == y1 + 4) || (x2 == x1 - 5 && y2 == y1 + 5) || (x2 == x1 - 6 && y2 == y1 + 6) ||
        (x2 == x1 - 7 && y2 == y1 + 7))
        return true
    }
    return false
  }


    //Königin - Queen
  def queenRule (x1: Int, y1: Int, x2: Int, y2: Int) : Boolean = {
    if (start.figure.get.name.equals("queen")) {
      //Vorwärts - Turm
      if ((x2 == x1 && y2 == y1 + 1) || (x2 == x1 && y2 == y1 + 2) || (x2 == x1 && y2 == y1 + 3) ||
        (x2 == x1 && y2 == y1 + 4) || (x2 == x1 && y2 == y1 + 5) || (x2 == x1 && y2 == y1 + 6) ||
        (x2 == x1 && y2 == y1 + 7) ||
        (x2 == x1 + 1 && y2 == y1) || (x2 == x1 + 2 && y2 == y1) || (x2 == x1 + 3 && y2 == y1) ||
        (x2 == x1 + 4 && y2 == y1) || (x2 == x1 + 5 && y2 == y1) || (x2 == x1 + 6 && y2 == y1) ||
        (x2 == x1 + 7 && y2 == y1) ||
        //Rückwärts - Turm
        (x2 == x1 && y2 == y1 - 1) || (x2 == x1 && y2 == y1 - 2) || (x2 == x1 && y2 == y1 - 3) ||
        (x2 == x1 && y2 == y1 - 4) || (x2 == x1 && y2 == y1 - 5) || (x2 == x1 && y2 == y1 - 6) ||
        (x2 == x1 && y2 == y1 - 7) ||
        (x2 == x1 - 1 && y2 == y1) || (x2 == x1 - 2 && y2 == y1) || (x2 == x1 - 3 && y2 == y1) ||
        (x2 == x1 - 4 && y2 == y1) || (x2 == x1 - 5 && y2 == y1) || (x2 == x1 - 6 && y2 == y1) ||
        (x2 == x1 - 7 && y2 == y1) ||
        //nach rechts oben
        (x2 == x1 + 1 && y2 == y1 - 1) || (x2 == x1 + 2 && y2 == y1 - 2) || (x2 == x1 + 3 && y2 == y1 - 3) ||
        (x2 == x1 + 4 && y2 == y1 - 4) || (x2 == x1 + 5 && y2 == y1 - 5) || (x2 == x1 + 6 && y2 == y1 - 6) ||
        (x2 == x1 + 7 && y2 == y1 - 7) ||
        //nach links oben
        (x2 == x1 - 1 && y2 == y1 - 1) || (x2 == x1 - 2 && y2 == y1 - 2) || (x2 == x1 - 3 && y2 == y1 - 3) ||
        (x2 == x1 - 4 && y2 == y1 - 4) || (x2 == x1 - 5 && y2 == y1 - 5) || (x2 == x1 - 6 && y2 == y1 - 6) ||
        (x2 == x1 - 7 && y2 == y1 - 7) ||
        //nach rechts unten
        (x2 == x1 + 1 && y2 == y1 + 1) || (x2 == x1 + 2 && y2 == y1 + 2) || (x2 == x1 + 3 && y2 == y1 + 3) ||
        (x2 == x1 + 4 && y2 == y1 + 4) || (x2 == x1 + 5 && y2 == y1 + 5) || (x2 == x1 + 6 && y2 == y1 + 6) ||
        (x2 == x1 + 7 && y2 == y1 + 7) ||
        //nach links unten
        (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 2 && y2 == y1 + 2) || (x2 == x1 - 3 && y2 == y1 + 3) ||
        (x2 == x1 - 4 && y2 == y1 + 4) || (x2 == x1 - 5 && y2 == y1 + 5) || (x2 == x1 - 6 && y2 == y1 + 6) ||
        (x2 == x1 - 7 && y2 == y1 + 7))
        return true
    }
    return false
  }
}

