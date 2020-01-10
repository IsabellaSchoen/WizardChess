package chess.model.boardComponent.boardMockImpl

import chess.model.Cell
import chess.model.boardComponent.{BoardTrait, boardBaseImpl}

class Board extends BoardTrait {
  override var state: Int = 0

  override def put(x: Char, y: Char, f: Char, c: Char): Board = this

  /**
    * überprüft, ob die KO, die man im Terminal eingibt, auch im Spielfeld exisitieren
    */
  override def validCoords(x1: Char, y1: Char, x2: Char, y2: Char): Boolean = true

  override val Matrix: Array[Array[Cell]] = _

  /**
    * Kontrolle der schwarzen Farbe
    */
  override def moveBlack(x1: Char, y1: Char, x2: Char, y2: Char): boardBaseImpl.Board = ???

  /**
    * Kontrolle der weissen Farbe
    */
  override def moveWhite(x1: Char, y1: Char, x2: Char, y2: Char): boardBaseImpl.Board = ???

  /**
    * Kontrolle, ob die Figure - abhängig von Farbe - bewegt werden darf; werfen integriert
    * Allgemein die Koordinaten
    * Implementierung der Anfangs- und Endknoten
    * Figur darf sich bewegen
    * Figur darf werfen
    */
  override def move(x1: Char, y1: Char, x2: Char, y2: Char): boardBaseImpl.Board = ???
}
