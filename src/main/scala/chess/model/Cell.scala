package chess.model

case class Cell(x: Int, y: Int, figure: Option[Figure] = None) {
  def isEmpty: Boolean = figure match {
    case Some(x) => false
    case None => true
  }

  //    val king : Option[Figure] = Some(Figure("King")
  def set(new_figure: String, color: Char = 'B', moved: Boolean = false): Cell = {
    if (!new_figure.equals("none"))
      copy(figure = Some(Figure(new_figure, color, moved)))
    else
      copy(figure = None)
  }

  override def toString: String = {
    if (figure.isDefined)
      figure.get + " " + (x + 'A').toChar + (y + 1)
    else
      "None " + (x + 'A').toChar + (y + 1)
  }
}


