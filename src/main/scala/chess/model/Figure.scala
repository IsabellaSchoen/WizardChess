package chess.model

abstract class Figure(color: Char) {
  def caption: Char = ' '
  def name: String = "none"
  def rule(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    checkStart(board, x1, y1) && checkEnd(board, x2, y2) && figRule(x1, y1, x2, y2)
  }

  def checkStart(board: Board, x1: Int, y1: Int): Boolean = {
    board.size > x1 && board.size > y1 && x1 >= 0 && y1 >= 0 && board.Matrix(x1)(y1).figure.get == this
  }

  def checkEnd(board: Board, x2: Int, y2: Int): Boolean = {
    board.size > x2 && board.size > y2 && x2 >= 0 && y2 >= 0 && board.Matrix(x2)(y2).isEmpty
  }

  def figRule(x1: Int, y1: Int, x2: Int, y2: Int) = false
}

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