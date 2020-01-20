package util

import chess.util.UndoManager
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers{

  "An UndoManager" should {
    val undoManager = new UndoManager

    "have a do, undo and redo" in {
      val command = new incrCommand
      command.state should be(1)
      undoManager.doStep(command)
      command.state should be(2)
      undoManager.doStep(command)
      command.state should be(3)
    }

    "handle multiple undo steps correctly" in {
      val command = new incrCommand
      command.state should be(1)
      undoManager.doStep(command)
      command.state should be(2)
      undoManager.doStep(command)
      command.state should be(3)
      undoManager.undoStep
      command.state should be(2)
      undoManager.undoStep
      command.state should be(1)
      undoManager.redoStep
      command.state should be(2)
    }
  }

}
