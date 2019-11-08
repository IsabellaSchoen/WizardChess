//64 Matrizen in einer ganzen Matrix
//Figuren bereits auf ihren Pläten

package chess.model

import chess.model.Matrix

case class Board(size: Int) {
    val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)

    override def toString: String = {
        val lineseparator = ("+-" + ("--" * size)) * size + "+\n"
        val line = ("| " + ("x " * size)) * size + "|\n"
        var box = "\n" + (lineseparator + (line * size)) * size + lineseparator
        for {
            row <- 0 until size
            col <- 0 until size
        } {
            val tmp = row match {
                case 0 => 'A'
                case 1 => 'B'
                case 2 => 'C'
                case 3 => 'D'
                case 4 => 'E'
                case 5 => 'F'
                case 6 => 'G'
                case 7 => 'H'
            }
            box = box.replaceFirst("x", Cell(tmp, col).toString)
        }
        box
    }
}