package chess.tui

import chess.model._

class Tui {

  def inputprocess(input: String, board: Board):Board = {
    input match {
      case "new" => BoardCreator(8).create //Erstellung eines Boards mit 64 Feldern und den Figuren
      case "exit" => board //Beenden des Programmes
      case _ => board //Beenden des Programmes
    }
  }
}
