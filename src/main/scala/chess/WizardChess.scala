package chess

import chess.aview.Tui
import model._

import scala.io.StdIn.readLine
import chess.control._


object WizardChess {
  var controller: Controller = new Controller(BoardCreator(8).init(BoardCreator(8).create))
  val tui = new Tui(controller)
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
