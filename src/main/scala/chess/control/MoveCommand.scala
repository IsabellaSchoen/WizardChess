package chess.control

import chess.control.controllerComponent.ControllerTrait
import chess.util.Command

class MoveCommand(controller: ControllerTrait, x1: Char, y1: Char, x2: Char, y2: Char) extends Command {
  override def doStep: Unit = controller.getState match {
    case 0 => controller.moveAll(x1, y1, x2, y2)
    case 1 => controller.moveOne(x1, y1, x2, y2)
    case 2 => controller.moveTwo(x1, y1, x2, y2)
  }

  override def undoStep: Unit = controller.back(x1, y1, x2, y2)

  override def redoStep: Unit = doStep
}
