package model

case class Cell(value: Char, value1: Int, var figure: Option[Figure]) {
  def isEmpty : Boolean = figure match {
    case Some(x) => false
    case None => true
  }

//    val king : Option[Figure] = Some(new Figure("King")
  val x = value
  val y = value1
  def getX = x
  def getY = y

}
