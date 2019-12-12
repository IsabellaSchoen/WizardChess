package chess.aview.gui

import chess.control.Controller
import chess.util.Observer
import scala.swing._

class SwingGui(controller: Controller) extends Frame with Observer {

  //listenTo(controller)
  title = "Chess - Welcome to your game"
  visible = true



  contents = new Button("Wizard Chess")
  contents = new Button ("Chess")

  contents = new Button("White")
  contents = new Button("Black")

  override def update(): Unit = {
    contents = SwingGui.
  }
}
