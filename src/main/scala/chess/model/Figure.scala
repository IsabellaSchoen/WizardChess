package chess.model

case class Figure(name: String = "pawn") {
  override def toString: String = name

  def caption: Char = {
    name match {
      case "pawn" => 'P'
      case "king" => 'K'
      case "bishop" => 'B'
      case "queen" => 'Q'
      case "knight" => 'k'
      case "rook" => 'R'
      case _ => ' '
    }
  }
}