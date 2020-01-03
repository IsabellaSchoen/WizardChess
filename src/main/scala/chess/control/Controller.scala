package chess.control

import chess.model._
import chess.util.Observable

class Controller(var board: Board) extends Observable {
  private var state: Int = 1
  def put(x: Char, y: Char, f: Char, c: Char): Unit = {
    board = board.put(x, y, f, c)
    notifyObservers()
  }

  def empty(): Unit = {
    board = BoardCreator(8).create
    notifyObservers()
  }

  def boardToString: String = board.toString

  def setState(state: Int): Unit = {
    this.state = state
    notifyObservers()
  }

  def move(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    state match {
      case 0 => moveAll(x1, y1, x2, y2)
      case 1 => moveOne(x1, y1, x2, y2)
      case 2 => moveTwo(x1, y1, x2, y2)
    }
  }

  def moveAll(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.move(x1, y1, x2, y2)
    notifyObservers()
  }

  def moveOne(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.moveWhite(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  def moveTwo(x1: Char, y1: Char, x2: Char, y2: Char) : Unit = {
    board = board.moveBlack(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  def create(): Unit = {
    board = BoardCreator(8).init(BoardCreator(8).create)
    notifyObservers()
  }

  def getFig(i: Int, j: Int): String = {
    board.Matrix(i)(j).figure.toString match {
      case "pawn" => "pawn"
      case "king" => "king_black"
      case "queen" => "queen_black"
      case "rook" => "rook_black"
      case "horse" => "horse_black"
      case "bishop" => "bishop_black"
      case _ => ""
    }
  }

}
