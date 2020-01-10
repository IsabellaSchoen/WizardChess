package chess.control.controllerComponent.controllerMockImpl

import chess.control.controllerComponent.ControllerTrait

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

  override def getFig(i: Int, j: Int): String = ""
}
