package model

case class Figure(name: String = "pawn") {
  override def toString: String = name
}