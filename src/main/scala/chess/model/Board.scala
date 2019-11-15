//64 Matrizen in einer ganzen Matrix
//Figuren bereits auf ihren Pläten

package chess.model

case class Board(size: Int) {
  val Matrix: Array[Array[Cell]] = Array.ofDim[Cell](size, size)

  def move(x1: Char, y1: Char, x2: Char, y2: Char): Board = {
    if ((xi(x1) == -1) || (yi(y1) == -1) || (xi(x2) == -1) || (yi(y2) == -1)) {
      println("not allowed!")
      return this
    }
    val tmp: Cell = Matrix(xi(x1))(yi(y1))
    if (Matrix(xi(x2))(yi(y2)).figure.isEmpty) {
      Matrix(xi(x2))(yi(y2)) = Matrix(xi(x2))(yi(y2)).set(tmp.figure.get.toString, tmp.figure.get.color)
      Matrix(xi(x1))(yi(y1)) = tmp.set("none")
    } else
      println("Not allowed")
    this
  }

  override def toString: String = {
    if (Matrix(0)(0) == null)
      return "empty Board"
    val numbers = "   A  B  C  D  E  F  G  H\n"
    val lineseparator = ("  " + "+-" + ("--" * (2 * size))) + "+" + "\n"
    val line1 = ("y " + (("\u001b[47;1m x \u001b[0m x ") * (size / 2))) + "\n"
    val line2 = ("y " + ((" x \u001b[47;1m x \u001b[0m") * (size / 2))) + "\n"
    val sep = "  " + "--" * 2 * size + "\n"
    var box = "\n" + numbers + (((line1 + line2) * ((size / 2))))
    for {
      row <- 0 until size
      col <- 0 until size
    } {
      box = box.replaceFirst("y", (col + 1).toString)
      if (Matrix(col)(row).figure.isDefined)
        if (Matrix(col)(row).figure.get.color.equals('W'))
          box = box.replaceFirst("x ", "\u001b[38;5;27;1m" + Matrix(col)(row).figure.get.caption.toString + " \u001b[0m")
        else
          box = box.replaceFirst("x ", "\u001b[38;5;196;1m" + Matrix(col)(row).figure.get.caption.toString + " \u001b[0m")
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

