package chess

import chess.aview.Tui
import chess.aview.gui.Gui
import chess.control.controllerComponent.{Controller, ControllerTrait}
import chess.model._
import com.google.inject.{Guice, Injector}

import scala.io.StdIn.readLine


object WizardChess {
  var controller: ControllerTrait = Controller(BoardCreator(8).init(BoardCreator(8).create))
  val tui = new Tui(controller)

  controller.notifyObservers()
  val injector: Injector = Guice.createInjector(new WizardChessModule)

  def main(args:Array[String]): Unit = {
    if(args.length == 0) {
      val gui = new Gui(controller)
      gui.main(Array())
    }
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
