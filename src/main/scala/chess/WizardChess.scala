package chess

import model._
import tui.Tui

import scala.io.StdIn.readLine


object WizardChess {
  var board: Board = BoardCreator(8).create
  val tui = new Tui

  def main(args:Array[String]): Unit = {
    var input:String = ""

    do {
      println(board)
      input = readLine()
      board = tui.inputprocess(input, board)
    } while (input != "exit")
  }
}
