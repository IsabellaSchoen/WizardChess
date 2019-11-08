package chess.model

case class Cell(x: Int, y: Int, figure: Option[Figure] = None) {
  def isEmpty: Boolean = figure match {
    case Some(x) => false
    case None => true
  }

  //    val king : Option[Figure] = Some(Figure("King")
  def set(new_figure: String): Cell = copy(figure = Some(Figure(new_figure)))

  override def toString: String = {
    if (figure.isDefined)
      figure.get + " " + x + y
    else
      "None " + x + y
  }
}


