//64 Matrizen in einer ganzen Matrix
//Figuren bereits auf ihren Pläten

package chess.model

import chess.model.Matrix

case class Board(size: Int) {
  val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)

  def move(x1: Char, y1: Char, x2: Char, y2: Char): Board = {
    val tmp: Cell = Matrix(index(x1))(y1 - '1')
    if (Matrix(index(x2))(y2 - '1').figure.isEmpty) {
      Matrix(index(x2))(y2 - '1') = Matrix(index(x2))(y2 - '1').set(tmp.figure.get.toString)
      Matrix(index(x1))(y1 - '1') = tmp.set("none")
    } else
    println("Not allowed")
    this
  }

  override def toString: String = {
    val numbers = "    A   B   C   D   E   F   G   H\n"
    val lineseparator = ("  +-" + ("--" * (2 * size - 1))) + "+\n"
    val line = ("y | " + ("x | " * size)) + "\n"
    val sep = "  " + "--" * 2 * size + "\n"
    var box = "\n" +numbers + (lineseparator + ((line + sep) * (size - 1))) + line + lineseparator
    for {
      row <- 0 until size
      col <- 0 until size
    } {
      box = box.replaceFirst("y", (col + 1).toString)
      if (Matrix(col)(row).figure.isDefined)
        box = box.replaceFirst("x", Matrix(col)(row).figure.get.caption.toString)
      else
        box = box.replaceFirst("x", " ")
    }
    box
  }

  def index(x: Char): Int  = x - 'A'
}

