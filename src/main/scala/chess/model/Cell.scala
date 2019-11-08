package chess.model

case class Cell(x: Char, y: Int, figure: Option[Figure] = None) {
  def isEmpty: Boolean = figure match {
    case Some(x) => false
    case None => true
  }

  //    val king : Option[Figure] = Some(Figure("King")
  def set(new_figure: String): Cell = copy(figure = Some(Figure(new_figure)))

  def getX: Int = {
    x match {
      case 'A' => 0
      case 'B' => 1
      case 'C' => 2
      case 'D' => 3
      case 'E' => 4
      case 'F' => 5
      case 'G' => 6
      case 'H' => 7
    }
  }

  def getY: Int = y - 1

  override def toString: String = {
    if (figure.isDefined)
      figure.get + " " + x + y
    else
      "None " + x + y
  }
}

/*lass cell(x: Char, y: Int) {
  var figure = null.asInstanceOf[figure]
  val posX = x
  val posY = y

  override def toString: String = {
    posX.toString + posY.toString
  }
}*/
