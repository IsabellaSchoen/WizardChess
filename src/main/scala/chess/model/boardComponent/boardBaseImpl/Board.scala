package chess.model.boardComponent.boardBaseImpl

import chess.model.boardComponent.BoardTrait
import chess.model.{Cell, Figure, None, RulesAll, RulesBlack, RulesWhite}

case class Board(size: Int) extends BoardTrait {
  override var state: Int = 0

  override def put(x: Char, y: Char, f: Char, c: Char): BoardTrait = {
    if (xi(x) >= 0 && xi(x) < size && yi(y) >= 0 && yi(y) < size && (c.equals('B') || c.equals('W')))
      Matrix(xi(x))(yi(y)) = Matrix(xi(x))(yi(y)).set(Figure.translate(f), c)
    this
  }

  /**
    * überprüft, ob die KO, die man im Terminal eingibt, auch im Spielfeld exisitieren
    */
  override def validCoords(x1: Char, y1: Char, x2: Char, y2: Char): Boolean =
    (xi(x1) != -1) && (yi(y1) != -1) && (xi(x2) != -1) && (yi(y2) != -1)


  override val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)


  /**
    * Kontrolle der schwarzen Farbe
    */
  override def moveBlack(x1: Char, y1: Char, x2: Char, y2: Char): BoardTrait = {
    if (!validCoords(x1, y1, x2, y2)) {
      println("Move not allowed cause of wrong KOs")
      state = 1
      return this
    }
    if (!RulesBlack.valid(this, xi(x1), yi(y1), xi(x2), yi(y2))) {
      println("Not a valid move because not an allowed rule for black colour!")
      state = 1
      return this
    }
    state = 0
    move(x1, y1, x2, y2)
  }


  /**
    * Kontrolle der weissen Farbe
    */
  override def moveWhite(x1: Char, y1: Char, x2: Char, y2: Char): BoardTrait = {
    if (!validCoords(x1, y1, x2, y2)) {
      println("Move not allowed cause of wrong KOs") //
      state = 0
      return this
    }
    if (!RulesWhite.valid(this, xi(x1), yi(y1), xi(x2), yi(y2))) {
      println("Not a valid move because not an allowed rule for white color!")
      state = 0
      return this
    }
    state = 1
    move(x1, y1, x2, y2)
  }


  /**
    * Kontrolle, ob die Figure - abhängig von Farbe - bewegt werden darf; werfen integriert
    * Allgemein die Koordinaten
    * Implementierung der Anfangs- und Endknoten
    * Figur darf sich bewegen
    * Figur darf werfen
    */
  override def move(x1: Char, y1: Char, x2: Char, y2: Char): BoardTrait = {
    val start: Cell = Matrix(xi(x1))(yi(y1))
    val end: Cell = Matrix(xi(x2))(yi(y2))
    if (end.isEmpty && RulesAll.valid(this, xi(x1), yi(y1), xi(x2), yi(y2))) {
      Matrix(xi(x2))(yi(y2)) = end.mv(start.figure)
      Matrix(xi(x1))(yi(y1)) = start.set("none")
    } else if(!end.isEmpty && RulesAll.hit(this, xi(x1), yi(y1), xi(x2), yi(y2)) && (!end.figure.col.equals(start.figure.col))) {
      Matrix(xi(x2))(yi(y2)) = end.mv(start.figure)
      Matrix(xi(x1))(yi(y1)) = start.set("none")
    } else
      println("Not allowed to move because of wrong used rule!")
      state match {  // state beibehalten, falls Zug nicht gültig
        case 1 => state = 0
        case 0 => state = 1
      }
    this
  }

  def back(x1: Char, y1: Char, x2: Char, y2: Char): Board = {
    val start: Cell = Matrix(xi(x1))(yi(y1))
    val end: Cell = Matrix(xi(x2))(yi(y2))
    Matrix(xi(x1))(yi(y1)) = start.mv(end.figure)
    Matrix(xi(x2))(yi(y2)) = end.set("none")
    Matrix(xi(x1))(yi(y1)).figure match {
      case pawn: Pawn => pawn.PawnState.atStart(yi(y1))
      case _ =>
    }
    this
  }


  override def toString: String = {
    if (Matrix(0)(0) == null)
      return "empty Board"
    val numbers = "   A  B  C  D  E  F  G  H\n"
    val line1 = ("y " + ((Console.WHITE_B + " x " + Console.RESET + " x ") * (size / 2))) + "\n"
    val line2 = ("y " + ((" x " + Console.WHITE_B + " x " + Console.RESET) * (size / 2))) + "\n"
    var box = "\n" + numbers + ((line1 + line2) * (size / 2))
    for {
      row <- 0 until size
      col <- 0 until size
    } {
      box = box.replaceFirst("y", (col + 1).toString)
      if (!Matrix(col)(row).figure.isInstanceOf[None])
        if (Matrix(col)(row).figure.col.equals('W'))
          box = box.replaceFirst("x ", "\u001b[34;1m" + Matrix(col)(row).figure.caption.toString + " " + Console.RESET)
        else
          box = box.replaceFirst("x ", "\u001b[31;1m" + Matrix(col)(row).figure.caption.toString + " " + Console.RESET)
      else
        box = box.replaceFirst("x", " ")
    }
    box
  }

  def xi(x: Char): Int = {
    var tmp: Int = x - 'A'
    if (tmp >= 0 && tmp < size)
      tmp
    else {
      tmp = x - 'a'
      if (tmp >= 0 && tmp < size)
        tmp
      else
        -1
    }
  }

  def yi(y: Char): Int = {
    val tmp: Int = y - '1'
    if (tmp >= 0 && tmp < size)
      tmp
    else
      -1
  }
}
