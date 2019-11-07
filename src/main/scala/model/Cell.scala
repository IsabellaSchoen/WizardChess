package model

case class Cell(x: Char, y: Int, figure: Option[Figure] = None) {
  def isEmpty: Boolean = figure match {
    case Some(x) => false
    case None => true
  }

  //    val king : Option[Figure] = Some(Figure("King")
  def set(new_figure: String): Cell = copy(figure = Some(Figure(new_figure)))

  override def toString: String = {
    figure.get.toString + " " + x.toString + y.toString
  }
}