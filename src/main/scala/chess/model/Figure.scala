package chess.model

case class Figure(name: String, color: Char, moved: Boolean) {
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

object Figure {
  def translate(f: Char): String = {
    f match {
      case 'P' | 'p' => "pawn"
      case 'K' | 'k' => "king"
      case 'B' | 'b' => "bishop"
      case 'Q' | 'q' => "queen"
      case 'H' | 'h' => "horse"
      case 'R' | 'r' => "rook"
      case _ => "none"
    }
  }

  def apply(name: String = "pawn", color: Char = 'B', moved: Boolean = false): Figure = new Figure(name, color, moved)
}