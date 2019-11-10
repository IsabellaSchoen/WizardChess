package chess.tui

import chess.model._

class Tui {

  def inputprocess(input: String, board: Board):Board = {
    input match {
      case "new" => BoardCreator(8).create //Erstellung eines Boards mit 64 Feldern und den Figuren
      case "exit" => board //Beenden des Programmes
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case x1 :: y1 :: x2 :: y2 :: Nil => board.move(x1, y1, x2, y2)
          case _ => board //Beenden des Programmes
        }
    }
  }
}