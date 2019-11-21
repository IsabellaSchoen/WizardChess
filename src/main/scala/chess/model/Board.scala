//64 Matrizen in einer ganzen Matrix
//Figuren bereits auf ihren Pläten

package chess.model

import chess.model.Rules

case class Board(size: Int) {
  val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)

  def move(x1: Char, y1: Char, x2: Char, y2: Char): Board = {
    if ((xi(x1) == -1) || (yi(y1) == -1) || (xi(x2) == -1) || (yi(y2) == -1)) {
      println("not allowed!")
      return this
    }
    if (!Rules.valid(this, xi(x1), yi(y1), xi(x2), yi(y2))) {
      println("Not a valid move!")
      return this
    }
    val start: Cell = Matrix(xi(x1))(yi(y1))
    val end: Cell = Matrix(xi(x2))(yi(y2))
    if (end.figure.isEmpty) {
      Matrix(xi(x2))(yi(y2)) = end.set(start.figure.get.toString, start.figure.get.color, moved = true)
      Matrix(xi(x1))(yi(y1)) = start.set("none")
    } else
      println("Not allowed")
    this
  }

  override def toString: String = {
    if (Matrix(0)(0) == null)
      return "empty Board"
    val numbers = "   A  B  C  D  E  F  G  H\n"
    val lineseparator = ("  " + "+-" + ("--" * (2 * size))) + "+" + "\n"
    val line1 = ("y " + ((Console.WHITE_B + " x " + Console.RESET + " x ") * (size / 2))) + "\n"
    val line2 = ("y " + ((" x " + Console.WHITE_B  + " x " + Console.RESET) * (size / 2))) + "\n"
    val sep = "  " + "--" * 2 * size + "\n"
    var box = "\n" + numbers + ((line1 + line2) * (size / 2))
    for {
      row <- 0 until size
      col <- 0 until size
    } {
      box = box.replaceFirst("y", (col + 1).toString)
      if (Matrix(col)(row).figure.isDefined)
        if (Matrix(col)(row).figure.get.color.equals('W'))
          box = box.replaceFirst("x ", Console.BLUE + Matrix(col)(row).figure.get.caption.toString + " " + Console.RESET)
        else
          box = box.replaceFirst("x ", Console.UNDERLINED + Matrix(col)(row).figure.get.caption.toString + " " + Console.RESET)
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

