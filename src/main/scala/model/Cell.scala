package model

case class Cell(value: Char, value1: Int, figure: Option[Figure]= None) {
  def isEmpty : Boolean = figure match {
    case Some(x) => false
    case None => true
  }

//    val king : Option[Figure] = Some(new Figure("King")
  def set(new_figure:Figure): Cell = copy(figure = Some(new_figure))

  val x = value
  val y = value1
  def getX = x
  def getY = y

}
