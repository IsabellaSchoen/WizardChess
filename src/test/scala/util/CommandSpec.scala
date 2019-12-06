package util

import chess.util.Command
import org.scalatest.{Matchers, WordSpec}

class incrCommand extends Command{

  var state:Int = 1

  override def doStep: Unit = state+=1
  override def undoStep: Unit = state-=1
  override def redoStep: Unit = state+=1
}


class CommandSpec extends WordSpec with Matchers {
  "A Command" should {
    "have a do step" in{
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.doStep
      command.state should be(2)
    }
    "have an undo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.doStep
      command.state should be(2)
    }
    "have a redo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.doStep
      command.state should be(2)
    }
  }
}
