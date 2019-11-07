package chess

import model.{Board,Cell,Figure}
import tui.Tui

import scala.io.StdIn.readLine


object WizardChess {
  var board = new Board(64)
  val tui = new Tui

  def main(args:Array[String]): Unit = {
    var input:String = ""

    do {
      input = readLine()
      board = tui.inputprocess(input, board)
    } while (input != "exit")
  }
}
