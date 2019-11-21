package tui

import chess.control.Controller
import chess.model.BoardCreator
import chess.tui.Tui
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {
  "A tui" should {
    "tell the controller to create a new board" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("new") should be (-2)
    }
    "be able to stop the programm" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("exit") should be (-1)
    }
    "tell the controller to move one figure to another position" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("A2 A4") should be (1)
    }
    "not react to other inputs" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("random input") should be (0)
    }
  }
}
