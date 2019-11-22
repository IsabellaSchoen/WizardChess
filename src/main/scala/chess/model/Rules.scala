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


    //Turm - rook
    if (start.figure.get.name.equals("rook")) {
      if (start.figure.get.moved) {
        if (start.figure.get.color.equals('B')) {
          if ((x2 == x1 && y2 == y1 + 1) || (x2 == x1 && y2 == y1 + 2) || (x2 == x1 && y2 == y1 + 3) ||
            (x2 == x1 && y2 == y1 + 4) || (x2 == x1 && y2 == y1 + 5) || (x2 == x1 && y2 == y1 + 6) ||
            (x2 == x1 && y2 == y1 + 7) ||
            (x2 == x1 +1 && y2 == y1) || (x2 == x1 + 2 && y2 == y1) || (x2 == x1 + 3 && y2 == y1) ||
            (x2 == x1 + 4 && y2 == y1) || (x2 == x1+5 && y2 == y1) || (x2 == x1+6 && y2 == y1) ||
            (x2 == x1+7 && y2 == y1) ||
            //Rückwärts
            (x2 == x1 && y2 == y1 - 1) || (x2 == x1 && y2 == y1 - 2) || (x2 == x1 && y2 == y1 - 3) ||
            (x2 == x1 && y2 == y1 - 4) || (x2 == x1 && y2 == y1 - 5) || (x2 == x1 && y2 == y1 - 6) ||
            (x2 == x1 && y2 == y1 - 7) ||
            (x2 == x1 -1 && y2 == y1) || (x2 == x1 - 2 && y2 == y1) || (x2 == x1 - 3 && y2 == y1) ||
            (x2 == x1 - 4 && y2 == y1) || (x2 == x1-5 && y2 == y1) || (x2 == x1-6 && y2 == y1) ||
            (x2 == x1-7 && y2 == y1))
            return true
        } else if (start.figure.get.color.equals('W')) {
          if ((x2 == x1 && y2 == y1 - 1) || (x2 == x1 && y2 == y1 - 2) || (x2 == x1 && y2 == y1 - 3) ||
            (x2 == x1 && y2 == y1 - 4) || (x2 == x1 && y2 == y1 - 5) || (x2 == x1 && y2 == y1 - 6) ||
            (x2 == x1 && y2 == y1 - 7) ||
            (x2 == x1 -1 && y2 == y1) || (x2 == x1 - 2 && y2 == y1) || (x2 == x1 - 3 && y2 == y1) ||
            (x2 == x1 - 4 && y2 == y1) || (x2 == x1-5 && y2 == y1) || (x2 == x1-6 && y2 == y1) ||
            (x2 == x1-7 && y2 == y1) ||
            //Rückwärts
            (x2 == x1 && y2 == y1 + 1) || (x2 == x1 && y2 == y1 + 2) || (x2 == x1 && y2 == y1 + 3) ||
            (x2 == x1 && y2 == y1 + 4) || (x2 == x1 && y2 == y1 + 5) || (x2 == x1 && y2 == y1 + 6) ||
            (x2 == x1 && y2 == y1 + 7) ||
            (x2 == x1 +1 && y2 == y1) || (x2 == x1 + 2 && y2 == y1) || (x2 == x1 + 3 && y2 == y1) ||
            (x2 == x1 + 4 && y2 == y1) || (x2 == x1+5 && y2 == y1) || (x2 == x1+6 && y2 == y1) ||
            (x2 == x1+7 && y2 == y1))
            return true
        }
      }
    }


    //Pferd - horse
    if (start.figure.get.name.equals("horse")) {
      if (start.figure.get.moved) {
        if (start.figure.get.color.equals('B')) {
          if ((x2 == x1 - 2 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 2) || (x2 == x1 - 1 && y2 == y1 + 2) ||
            (x2 == x1 + 1 && y2 == y1 + 2) || (x2 == x1 + 2 && y2 == y1 + 1) ||
            //Rückwärts
            (x2 == x1 + 2 && y2 == y1 - 1) || (x2 == x1 + 1 && y2 == y1 - 2) || (x2 == x1 + 1 && y2 == y1 - 2) ||
            (x2 == x1 - 1 && y2 == y1 - 2) || (x2 == x1 - 2 && y2 == y1 - 1))
            return true
        } else if (start.figure.get.color.equals('W')) {
          if ((x2 == x1 + 2 && y2 == y1 - 1) || (x2 == x1 + 1 && y2 == y1 - 2) || (x2 == x1 + 1 && y2 == y1 - 2) ||
            (x2 == x1 - 1 && y2 == y1 - 2) || (x2 == x1 - 2 && y2 == y1 - 1) ||
            //Rückwärts
            (x2 == x1 - 2 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 2) || (x2 == x1 - 1 && y2 == y1 + 2) ||
            (x2 == x1 + 1 && y2 == y1 + 2) || (x2 == x1 + 2 && y2 == y1 + 1))
            return true
        }
      }
    }



    //Läufer - bishop
    if (start.figure.get.name.equals("bishop")) {
      if (start.figure.get.moved) {
        if (start.figure.get.color.equals('B')) {
          //nach rechts - vorwärts
            if ((x2 == x1 +1 && y2 == y1 + 1) || (x2 == x1 +2 && y2 == y1 + 2) || (x2 == x1 +3 && y2 == y1 + 3) ||
              (x2 == x1 +4 && y2 == y1 + 4) || (x2 == x1 +5 && y2 == y1 + 5) || (x2 == x1 +6 && y2 == y1 + 6) ||
              (x2 == x1 +7 && y2 == y1 + 7) ||
              //nach links - vorwärts
              (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
              (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
              (x2 == x1 - 1 && y2 == y1 + 1) ||
              //nach rechts - rückwärts
              (x2 == x1 -1 && y2 == y1 + 1) || (x2 == x1 -2 && y2 == y1 + 2) || (x2 == x1 -3 && y2 == y1 + 3) ||
              (x2 == x1 -4 && y2 == y1 + 4) || (x2 == x1 -5 && y2 == y1 + 5) || (x2 == x1 -6 && y2 == y1 + 6) ||
              (x2 == x1 -7 && y2 == y1 + 7) ||
              //nach links - rückwärts
              (x2 == x1 + 1 && y2 == y1 - 1) || (x2 == x1 + 2 && y2 == y1 - 2) || (x2 == x1 + 3 && y2 == y1 - 3) ||
              (x2 == x1 + 4 && y2 == y1 - 4) || (x2 == x1 + 5 && y2 == y1 - 5) || (x2 == x1 + 6 && y2 == y1 - 6) ||
              (x2 == x1 + 7 && y2 == y1 - 7))
            return true
        } else if (start.figure.get.color.equals('W')) {
          //nach rechts - vorwärts
          if ((x2 == x1 -1 && y2 == y1 + 1) || (x2 == x1 -2 && y2 == y1 + 2) || (x2 == x1 -3 && y2 == y1 + 3) ||
            (x2 == x1 -4 && y2 == y1 + 4) || (x2 == x1 -5 && y2 == y1 + 5) || (x2 == x1 -6 && y2 == y1 + 6) ||
            (x2 == x1 -7 && y2 == y1 + 7) ||
            //nach links - vorwärts
            (x2 == x1 + 1 && y2 == y1 - 1) || (x2 == x1 + 2 && y2 == y1 - 2) || (x2 == x1 + 3 && y2 == y1 - 3) ||
            (x2 == x1 + 4 && y2 == y1 - 4) || (x2 == x1 + 5 && y2 == y1 - 5) || (x2 == x1 + 6 && y2 == y1 - 6) ||
            (x2 == x1 + 7 && y2 == y1 - 7) ||
          //nach rechts - rückwärts
            (x2 == x1 +1 && y2 == y1 + 1) || (x2 == x1 +2 && y2 == y1 + 2) || (x2 == x1 +3 && y2 == y1 + 3) ||
            (x2 == x1 +4 && y2 == y1 + 4) || (x2 == x1 +5 && y2 == y1 + 5) || (x2 == x1 +6 && y2 == y1 + 6) ||
            (x2 == x1 +7 && y2 == y1 + 7) ||
            //nach links - rückwärts
            (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
            (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
            (x2 == x1 - 1 && y2 == y1 + 1))
            return true
        }
      }
    }


    //Königin - Queen
    if (start.figure.get.name.equals("queen")) {
      if (start.figure.get.moved) {
        if (start.figure.get.color.equals('B')) {
          //Vorwärts - Turm
          if ((x2 == x1 && y2 == y1 + 1) || (x2 == x1 && y2 == y1 + 2) || (x2 == x1 && y2 == y1 + 3) ||
            (x2 == x1 && y2 == y1 + 4) || (x2 == x1 && y2 == y1 + 5) || (x2 == x1 && y2 == y1 + 6) ||
            (x2 == x1 && y2 == y1 + 7) ||
            (x2 == x1 +1 && y2 == y1) || (x2 == x1 + 2 && y2 == y1) || (x2 == x1 + 3 && y2 == y1) ||
            (x2 == x1 + 4 && y2 == y1) || (x2 == x1+5 && y2 == y1) || (x2 == x1+6 && y2 == y1) ||
            (x2 == x1+7 && y2 == y1) ||
            //Rückwärts - Turm
            (x2 == x1 && y2 == y1 - 1) || (x2 == x1 && y2 == y1 - 2) || (x2 == x1 && y2 == y1 - 3) ||
            (x2 == x1 && y2 == y1 - 4) || (x2 == x1 && y2 == y1 - 5) || (x2 == x1 && y2 == y1 - 6) ||
            (x2 == x1 && y2 == y1 - 7) ||
            (x2 == x1 -1 && y2 == y1) || (x2 == x1 - 2 && y2 == y1) || (x2 == x1 - 3 && y2 == y1) ||
            (x2 == x1 - 4 && y2 == y1) || (x2 == x1-5 && y2 == y1) || (x2 == x1-6 && y2 == y1) ||
            (x2 == x1-7 && y2 == y1) ||
            //nach rechts - vorwärts - Läufer
            (x2 == x1 +1 && y2 == y1 + 1) || (x2 == x1 +2 && y2 == y1 + 2) || (x2 == x1 +3 && y2 == y1 + 3) ||
            (x2 == x1 +4 && y2 == y1 + 4) || (x2 == x1 +5 && y2 == y1 + 5) || (x2 == x1 +6 && y2 == y1 + 6) ||
            (x2 == x1 +7 && y2 == y1 + 7) ||
            //nach links - vorwärts - Läufer
            (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
            (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
            (x2 == x1 - 1 && y2 == y1 + 1) ||
            //nach rechts - rückwärts - Läufer
            (x2 == x1 -1 && y2 == y1 + 1) || (x2 == x1 -2 && y2 == y1 + 2) || (x2 == x1 -3 && y2 == y1 + 3) ||
            (x2 == x1 -4 && y2 == y1 + 4) || (x2 == x1 -5 && y2 == y1 + 5) || (x2 == x1 -6 && y2 == y1 + 6) ||
            (x2 == x1 -7 && y2 == y1 + 7) ||
            //nach links - rückwärts - Läufer
            (x2 == x1 + 1 && y2 == y1 - 1) || (x2 == x1 + 2 && y2 == y1 - 2) || (x2 == x1 + 3 && y2 == y1 - 3) ||
            (x2 == x1 + 4 && y2 == y1 - 4) || (x2 == x1 + 5 && y2 == y1 - 5) || (x2 == x1 + 6 && y2 == y1 - 6) ||
            (x2 == x1 + 7 && y2 == y1 - 7))
            return true
        } else if (start.figure.get.color.equals('W')) {
          //Vorwärts - Turm
          if ((x2 == x1 && y2 == y1 - 1) || (x2 == x1 && y2 == y1 - 2) || (x2 == x1 && y2 == y1 - 3) ||
            (x2 == x1 && y2 == y1 - 4) || (x2 == x1 && y2 == y1 - 5) || (x2 == x1 && y2 == y1 - 6) ||
            (x2 == x1 && y2 == y1 - 7) ||
            (x2 == x1 -1 && y2 == y1) || (x2 == x1 - 2 && y2 == y1) || (x2 == x1 - 3 && y2 == y1) ||
            (x2 == x1 - 4 && y2 == y1) || (x2 == x1-5 && y2 == y1) || (x2 == x1-6 && y2 == y1) ||
            (x2 == x1-7 && y2 == y1) ||
            //Rückwärts - Turm
            (x2 == x1 && y2 == y1 + 1) || (x2 == x1 && y2 == y1 + 2) || (x2 == x1 && y2 == y1 + 3) ||
            (x2 == x1 && y2 == y1 + 4) || (x2 == x1 && y2 == y1 + 5) || (x2 == x1 && y2 == y1 + 6) ||
            (x2 == x1 && y2 == y1 + 7) ||
            (x2 == x1 +1 && y2 == y1) || (x2 == x1 + 2 && y2 == y1) || (x2 == x1 + 3 && y2 == y1) ||
            (x2 == x1 + 4 && y2 == y1) || (x2 == x1+5 && y2 == y1) || (x2 == x1+6 && y2 == y1) ||
            (x2 == x1+7 && y2 == y1) ||
            //nach rechts - vorwärts - Läufer
            (x2 == x1 -1 && y2 == y1 + 1) || (x2 == x1 -2 && y2 == y1 + 2) || (x2 == x1 -3 && y2 == y1 + 3) ||
            (x2 == x1 -4 && y2 == y1 + 4) || (x2 == x1 -5 && y2 == y1 + 5) || (x2 == x1 -6 && y2 == y1 + 6) ||
            (x2 == x1 -7 && y2 == y1 + 7) ||
            //nach links - vorwärts - Läufer
            (x2 == x1 + 1 && y2 == y1 - 1) || (x2 == x1 + 2 && y2 == y1 - 2) || (x2 == x1 + 3 && y2 == y1 - 3) ||
            (x2 == x1 + 4 && y2 == y1 - 4) || (x2 == x1 + 5 && y2 == y1 - 5) || (x2 == x1 + 6 && y2 == y1 - 6) ||
            (x2 == x1 + 7 && y2 == y1 - 7) ||
            //nach rechts - rückwärts - Läufer
            (x2 == x1 +1 && y2 == y1 + 1) || (x2 == x1 +2 && y2 == y1 + 2) || (x2 == x1 +3 && y2 == y1 + 3) ||
            (x2 == x1 +4 && y2 == y1 + 4) || (x2 == x1 +5 && y2 == y1 + 5) || (x2 == x1 +6 && y2 == y1 + 6) ||
            (x2 == x1 +7 && y2 == y1 + 7) ||
            //nach links - rückwärts - Läufer
            (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
            (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) || (x2 == x1 - 1 && y2 == y1 + 1) ||
            (x2 == x1 - 1 && y2 == y1 + 1))
            return true
        }
      }
    }



    //König - king
    if (start.figure.get.name.equals("king")) {
      if (start.figure.get.moved) {
        if (start.figure.get.color.equals('B')) {
          if ((x2 == x1+1 && y2 == y1) || (x2 == x1 && y2 == y1 - 1) || (x2 == x1-1 && y2 == y1) ||
            (x2 == x1 && y2 == y1+1))
            return true
        } else if (start.figure.get.color.equals('W')) {
          if ((x2 == x1+1 && y2 == y1) || (x2 == x1 && y2 == y1 - 1) || (x2 == x1-1 && y2 == y1) ||
            (x2 == x1 && y2 == y1+1))
            return true
        }
      }
    }
    false
  }
}
