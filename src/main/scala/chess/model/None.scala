package chess.model

class None(color: Char) extends Figure(color) {
  override def caption: Char = ' '

  override def toString: String = "none"
}
