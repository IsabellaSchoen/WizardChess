package chess.control

import chess.model._
import chess.util.Observable

class Controller(var board: Board) extends Observable {

  def gridToString: String = board.toString

  def move(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.move(x1, y1, x2, y2)
    notifyObservers()
  }

  def create(): Unit = {
    board = BoardCreator(8).create
    notifyObservers()
  }

}