package chess

import model._
import tui.Tui
import scala.io.StdIn.readLine
import chess.control._


object WizardChess {
  var controller: Controller = new Controller(BoardCreator(8).create)
  val tui = new Tui(controller)
  controller.notifyObservers()

  def main(args:Array[String]): Unit = {
    //print(Console.RED + "hallo")
    var input:String = ""

    do {
      input = readLine()
      tui.inputprocess(input)
    } while (input != "exit")
  }
}
