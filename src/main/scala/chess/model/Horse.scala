package chess.model

class Horse(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    (Math.abs(x2 - x1) == 2 && Math.abs(y2 - y1) == 1) ||
      (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 2)
  }

  override def caption: Char = 'H'

  override def toString: String = "horse"
}
