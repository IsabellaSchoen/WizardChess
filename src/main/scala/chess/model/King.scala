package chess.model

/**
  * Figur king = König
  * die Figur darf nur einen Schritt nach links, rechts, geradeaus oder rückwärts machen
  */

class King(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    (Math.abs(x2 - x1) + Math.abs(y2 - y1)) == 1
  }

  override def caption: Char = 'K'

  override def toString: String = "king"
}
