//64 Matrizen in einer ganzen Matrix
//Figuren bereits auf ihren Pläten

package chess.model

import chess.model.Matrix

case class Board(size: Int) {
  val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)

  override def toString: String = {
    val lineseparator = ("+-" + ("--" * (2 * size - 1))) + "+\n"
    val line = ("| " + ("x | " * size)) + "\n"
    val sep = "--" * 2 * size + "\n"
    var box = "\n" + (lineseparator + ((line + sep) * (size - 1))) + line + lineseparator
    for {
      row <- 0 until size
      col <- 0 until size
    } {
      if (Matrix(row)(col).figure.isDefined)
        box = box.replaceFirst("x", Matrix(row)(col).figure.get.caption.toString)
      else
        box = box.replaceFirst("x", " ")
    }
    box
  }
}

