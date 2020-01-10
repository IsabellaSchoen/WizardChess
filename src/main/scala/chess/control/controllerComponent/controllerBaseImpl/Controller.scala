package chess.control.controllerComponent.controllerBaseImpl

import chess.control.controllerComponent.ControllerTrait
import chess.model._
import chess.model.boardComponent.BoardTrait
import chess.util.Observable

class Controller(var board: BoardTrait) extends Observable with ControllerTrait {
  private var state: Int = 1
  override def put(x: Char, y: Char, f: Char, c: Char): Unit = {
    board = board.put(x, y, f, c)
    notifyObservers()
  }

  override def empty(): Unit = {
    board = BoardCreator(8).create
    notifyObservers()
  }

  override def boardToString: String = board.toString

  override def setState(state: Int): Unit = {
    this.state = state
    notifyObservers()
  }

  override def move(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    state match {
      case 0 => moveAll(x1, y1, x2, y2)
      case 1 => moveOne(x1, y1, x2, y2)
      case 2 => moveTwo(x1, y1, x2, y2)
    }
  }

  override def moveAll(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.move(x1, y1, x2, y2)
    notifyObservers()
  }

  /**
    * der erste Player hat die weisse Farbe
    */
  override def moveOne(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.moveWhite(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  /**
    * der zweite Player hat die schwarze Farbe
    */
  override def moveTwo(x1: Char, y1: Char, x2: Char, y2: Char) : Unit = {
    board = board.moveBlack(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  override def create(): Unit = {
    board = BoardCreator(8).init(BoardCreator(8).create)
    notifyObservers()
  }

  override def getFig(i: Int, j: Int): String = {
    var tmp = board.Matrix(i)(j).figure.toString match {
      case "pawn" => "pawn"
      case "king" => "king"
      case "queen" => "queen"
      case "rook" => "rook"
      case "horse" => "horse"
      case "bishop" => "bishop"
      case _ => ""
    }
    tmp += "_"
    board.Matrix(i)(j).figure.col match {
      case 'B' => tmp += "black"
      case 'W' => tmp += "white"
    }
    tmp
  }

}