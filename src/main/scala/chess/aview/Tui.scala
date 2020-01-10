package chess.aview

import chess.control.controllerComponent.ControllerTrait
import chess.util.Observer

class Tui(controller: ControllerTrait) extends Observer {

  controller.add(this)

  def inputprocess(input: String): Int = {
    input match {
      case "new" => controller.create()
        -2// Erstellung eines Boards mit 64 Feldern und den Figuren
      case "exit" => -1// Beenden des Programmes
      case "e" => controller.empty()
        2
      case "deactivate players" => println("deactivated playersystem")
        controller.setState(0)
        5
      case "activate players" => println("activated playersystem")
        controller.setState(1)
        -5
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'p' :: f :: x :: y :: c :: Nil => controller.put(x, y, f, c)
            3
          case 'p' :: f :: x :: y :: Nil => controller.put(x, y, f, 'B')
            4
          case x1 :: y1 :: x2 :: y2 :: Nil => controller.move(x1, y1, x2, y2)
            1
          case _ => println("no valid command")
            0 // continue
        }
    }
  }

  override def update(): Unit =  println(controller.boardToString)

}