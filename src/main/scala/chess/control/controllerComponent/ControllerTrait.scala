package chess.control.controllerComponent

import chess.model.boardComponent.BoardTrait
import chess.util.Observable

trait ControllerTrait extends Observable {

  var board: BoardTrait

  def getState(): Int

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

  def getFig(b: BoardTrait, i: Int, j: Int): String

  def save(): Unit

  def load(): Unit

  def back(x1: Char, y1: Char, x2: Char, y2: Char): Unit

  def undo(): Unit

  def redo(): Unit

  var graveyardWhite: BoardTrait

  var graveyardBlack: BoardTrait
}

object Controller {
  def apply(board: BoardTrait): ControllerTrait = {
    new controllerBaseImpl.Controller(board)
  }
}