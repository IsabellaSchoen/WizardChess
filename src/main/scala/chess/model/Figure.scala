package chess.model

abstract class Figure(color: Char)

object Figure {
  def apply(name: String = "pawn", color: Char = 'B'): Figure = {
    name match {
      case "pawn" => new Pawn(color)
      case "king"
    }
  }
}