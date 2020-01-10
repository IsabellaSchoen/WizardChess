package chess.control.controllerComponent

import chess.model.boardComponent.BoardTrait
import chess.util.Observable

trait ControllerTrait extends Observable {

  def put(x: Char, y: Char, f: Char, c: Char): Unit

  def empty(): Unit

  def boardToString: String

  def setState(state: Int): Unit

  def move(x1: Char, y1: Char, x2: Char, y2: Char): Unit

  def moveAll(x1: Char, y1: Char, x2: Char, y2: Char): Unit

  /**
    * der erste Player hat die weisse Farbe
    */
  def moveOne(x1: Char, y1: Char, x2: Char, y2: Char): Unit

  /**
    * der zweite Player hat die schwarze Farbe
    */
  def moveTwo(x1: Char, y1: Char, x2: Char, y2: Char): Unit

  def create(): Unit

  def getFig(i: Int, j: Int): String
}

object Controller {
  def apply(board: BoardTrait): ControllerTrait = {
    new controllerBaseImpl.Controller(board)
  }
}