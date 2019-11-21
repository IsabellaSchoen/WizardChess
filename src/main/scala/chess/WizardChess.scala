package chess

import model._
import tui.Tui
import scala.io.StdIn.readLine
import chess.control._


object WizardChess {
  val controller: Controller = new Controller(BoardCreator(8).create)
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
