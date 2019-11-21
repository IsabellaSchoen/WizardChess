package chess.model

case class Figure(name: String = "pawn", color: Char = 'B', moved: Boolean = false) {
  override def toString: String = name

  def caption: Char = {
    name match {
      case "pawn" => 'P'
      case "king" => 'K'
      case "bishop" => 'B'
      case "queen" => 'Q'
      case "horse" => 'H'
      case "rook" => 'R'
      case _ => ' '
    }
  }
}