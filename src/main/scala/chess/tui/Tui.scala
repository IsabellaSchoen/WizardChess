package chess.tui

import chess.control.Controller
import chess.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def inputprocess(input: String): Unit = {
    input match {
      case "new" => controller.create() // Erstellung eines Boards mit 64 Feldern und den Figuren
      case "exit" => // Beenden des Programmes
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case x1 :: y1 :: x2 :: y2 :: Nil => controller.move(x1, y1, x2, y2)
          case _ => // continue
        }
    }
  }

  override def update(): Unit =  println(controller.gridToString)

}