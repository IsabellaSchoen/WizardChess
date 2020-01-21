package tui

import chess.aview.Tui
import chess.control.controllerComponent.controllerBaseImpl.Controller
import chess.model.BoardCreator
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
      new Tui(new Controller(BoardCreator(8).init(BoardCreator(8).create))).inputprocess("A2 A4") should be (1)
    }
    "not react to other inputs" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("random input") should be (0)
    }
    "be able to put figures on a board" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("ppa1") should be (4)
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("ppa1B") should be (3)
    }
    "be able to deactivate the player system" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("deactivate players") should be (5)
    }
    "be able to activate the player system" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("activate players") should be (-5)
    }
    "be able to create an empty board" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("e") should be (2)
    }
    "be able to save a board" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("save") should be (100)
    }
    "be able to load a board" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("load") should be (-100)
    }
    "be able to undo" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("undo") should be (40)
    }
    "be able to redo" in {
      new Tui(new Controller(BoardCreator(8).create)).inputprocess("redo") should be (-40)
    }
  }
}
