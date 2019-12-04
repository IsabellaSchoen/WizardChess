package chess.model

class Rook(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    (x1 == x2) || (y1 == y2)
  }

  override def caption: Char = 'R'

  override def toString: String = "rook"
}
