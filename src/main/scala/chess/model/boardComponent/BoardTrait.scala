package chess.model.boardComponent

import chess.model.Cell
import chess.model.boardComponent.boardBaseImpl.Board

trait BoardTrait {

  var state: Int

  def put(x: Char, y: Char, f: Char, c: Char): BoardTrait

  /**
    * überprüft, ob die KO, die man im Terminal eingibt, auch im Spielfeld exisitieren
    */
  def validCoords(x1: Char, y1: Char, x2: Char, y2: Char): Boolean

  val Matrix: Array[Array[Cell]]

  /**
    * Kontrolle der schwarzen Farbe
    */
  def moveBlack(x1: Char, y1: Char, x2: Char, y2: Char): BoardTrait

  /**
    * Kontrolle der weissen Farbe
    */
  def moveWhite(x1: Char, y1: Char, x2: Char, y2: Char): BoardTrait

  /**
    * Kontrolle, ob die Figure - abhängig von Farbe - bewegt werden darf; werfen integriert
    * Allgemein die Koordinaten
    * Implementierung der Anfangs- und Endknoten
    * Figur darf sich bewegen
    * Figur darf werfen
    */
  def move(x1: Char, y1: Char, x2: Char, y2: Char): BoardTrait
}
