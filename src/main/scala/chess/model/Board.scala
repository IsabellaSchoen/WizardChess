//64 Matrizen in einer ganzen Matrix
//Figuren bereits auf ihren Pläten

package chess.model

import chess.model.Matrix

case class Board(size: Int) {
    val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)
}