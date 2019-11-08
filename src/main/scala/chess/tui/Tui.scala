package chess.tui

import chess.model.{Board,Cell,Figure}

class Tui {

  def inputprocess(input: String, board: Board):Board = {
    input match {
      case "new" => Board(8) //Erstellung eines Boards mit 64 Feldern und den Figuren
      case "exit" => board //Beenden des Programmes
    }
  }
}
