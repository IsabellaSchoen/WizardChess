package chess

import chess.aview.Tui
import chess.aview.gui.Gui
import chess.control._
import chess.control.controllerComponent.controllerBaseImpl.Controller
import chess.model._

import scala.io.StdIn.readLine


object WizardChess {
  var controller: Controller = new Controller(BoardCreator(8).init(BoardCreator(8).create))
  val tui = new Tui(controller)
  val gui = new Gui(controller)
  gui.main(Array())
  controller.notifyObservers()

  def main(args:Array[String]): Unit = {
    var input: String = ""

    do {
      if (args.length == 0)
        input = readLine()
      else { // test
        if (!input.equals(args(0)))
          input = args(0)
        else
          input = args(1)
      }
      tui.inputprocess(input)
    } while (input != "exit")
  }
}
