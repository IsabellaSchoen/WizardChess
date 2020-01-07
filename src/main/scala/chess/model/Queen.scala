package chess.model

/**
  * Figur queen = Königin
  * die Figur ist eine Mischung aus Bishop und Rook
  * sie darf diagonal, geradeaus, nach links und rechts gehen
  */

class Queen(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    (Figure("rook").figRule(x1, y1, x2, y2) ||
      Figure("bishop").figRule(x1, y1, x2, y2))
  }

  override def caption: Char = 'Q'

  override def toString: String = "queen"
}
