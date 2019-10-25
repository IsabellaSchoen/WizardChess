package model

case class Cell(value: Char, value1: Int) {
  def isEmpty : Boolean = figure == null

    var figure : Figure = null

  val x = value
  val y = value1
  def getX = x
  def getY = y

}
