package chess.model

object RulesBlack {
  def valid(board: Board, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    if (board.Matrix(x1)(y1).isEmpty || board.Matrix(x1)(y1).figure.get.color != 'B')
      return false
    RulesAll.valid(board, x1, y1, x2, y2)
  }
}
