package chess.model

/**
  * Figur pawn = Bauer
  * der Bauer darf nur geradeaus gehen
  * wenn er eine Figur wirft, darf dies nur diagonal passieren
  * */


class Pawn(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = PawnState.handle(x1, y1, x2, y2)

  object PawnState {
    var state: () => Boolean = twoStep //Parameterlose Funktion, aber Rückgabewert ist boolea
    var xs, ys, xe, ye = -1

    def atStart(y: Int): Unit = {
      if ((color == 'B' && y == 1) || (color == 'W' && y == 6)) //Zielposition = Startposition?
        state = twoStep
    }

    def handle(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
      xs = x1; ys = y1; xe = x2; ye = y2
      val backup = state
      if (backup()) //() -> führe diese Funktion aus - Abfrage, darf er laufen
        state = oneStep //wenn er gelaufen ist, darf er nur noch oneStep, egal ob zwei oder einen Schritt
      backup() //Rückgabewert ist unser backup, da es geändert wird zu oneStep, wenn er gelaufen ist
    }

    /**
      * wenn der Bauer zwei Schritte am Anfang gehen darf, darf er danach
      * nur noch einen Schritt gehen
      */
    def twoStep: () => Boolean = {
      color match {
        case 'B' => () => oneStep() || (xs == xe && ye == ys + 2)
        case 'W' => () => oneStep() || (xs == xe && ye == ys - 2)
      }
    }

    /**
      * wenn der Bauer einen Schritt am Anfang gehen darf, darf er danach
      * nur noch einen Schritt gehen
      */
    def oneStep: () => Boolean = {
      color match {
        case 'B' => () => xs == xe && ye == ys + 1
        case 'W' => () => xs == xe && ye == ys - 1
      }
    }
  }


  /**
    * Wurfmethode für den Bauern
    * hierbei darf er nur diagonal in einem Schritt werfen
    * und dabei muss beachtet werden, dass sich nur unterschiedliche Farben werfen dürfen
    */
  def hit(xs: Int, ys: Int, xe: Int, ye: Int): Boolean = {
    color match {
      case 'B' => ye == ys + 1 && (xe == xs - 1 || xe == xs + 1)
      case 'W' => ye == ys - 1 && (xe == xs - 1 || xe == xs + 1)
    }
  }

  override def caption: Char = 'P'

  override def toString: String = "pawn"
}
