package chess.model

abstract class Figure(color: Char)

object Figure {
  def apply(name: String = "pawn", color: Char = 'B'): Figure = {
    name match {
      case "pawn" => new Pawn(color)
      case "king" => new King(color)
      case "queen" => new Queen(color)
      case "bishop" => new Bishop(color)
      case "horse" => new Horse(color)
      case "rook" => new Rook(color)
    }
  }
}