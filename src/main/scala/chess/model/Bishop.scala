package chess.model


/**
  * Figur Bishop = Läufe
  * Läufe darf diagonal gehen
  */
class Bishop(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    (Math.abs(x2 - x1) == Math.abs(y2 - y1))
  }

  override def caption: Char = 'B'

  override def toString: String = "bishop"
}
