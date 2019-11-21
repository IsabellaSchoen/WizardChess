package chess.tui

import chess.control.Controller
import chess.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def inputprocess(input: String): Int = {
    input match {
      case "new" => controller.create()
        -2// Erstellung eines Boards mit 64 Feldern und den Figuren
      case "exit" => -1// Beenden des Programmes
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case x1 :: y1 :: x2 :: y2 :: Nil => controller.move(x1, y1, x2, y2)
            1
          case _ => 0 // continue
        }
    }
  }

  override def update(): Unit =  println(controller.gridToString)

}