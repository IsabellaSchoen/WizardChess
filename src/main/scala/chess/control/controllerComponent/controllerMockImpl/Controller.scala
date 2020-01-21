package chess.control.controllerComponent.controllerMockImpl

import chess.control.controllerComponent.ControllerTrait
import chess.model.BoardCreator
import chess.model.boardComponent.{Board, BoardTrait}

class Controller extends ControllerTrait{
  override def put(x: Char, y: Char, f: Char, c: Char): Unit = {}

  override def empty(): Unit = {}

  override def boardToString: String = ""

  override def setState(state: Int): Unit = {}

  override def move(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {}

  override def moveAll(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {}

  /**
    * der erste Player hat die weisse Farbe
    */
  override def moveOne(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {}

  /**
    * der zweite Player hat die schwarze Farbe
    */
  override def moveTwo(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {}

  override def create(): Unit = {}

  override def getFig(board: BoardTrait, i: Int, j: Int): String = ""

  override def save(): Unit = {}

  override def load(): Unit = {}

  override def getState(): Int = 0

  override def undo(): Unit = {}

  override def redo(): Unit = {}

  override def back(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {}

  override var board: BoardTrait = BoardCreator(0).create
  override var graveyardWhite: BoardTrait = BoardCreator(0).create
  override var graveyardBlack: BoardTrait = BoardCreator(0).create
}
