package chess.model

case class Cell(x: Int, y: Int, figure: Figure = Figure("none")) {
  def isEmpty: Boolean = figure.isInstanceOf[None]

  //    val king : Option[Figure] = Some(Figure("King")
  def set(new_figure: String, color: Char = 'B', moved: Boolean = false): Cell = {
    if (!new_figure.equals("none"))
      copy(figure = Figure(new_figure, color))
    else
      copy(figure = Figure("none"))
  }

  def mv(fig: Figure): Cell = {
    copy(figure = fig)
  }

  override def toString: String = {
    if (!figure.isInstanceOf[None])
      figure + " " + (x + 'A').toChar + (y + 1)
    else
      "None " + (x + 'A').toChar + (y + 1)
  }
}


