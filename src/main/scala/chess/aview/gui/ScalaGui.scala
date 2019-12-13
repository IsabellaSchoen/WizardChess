package chess.aview.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.{Node, Scene}
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.text.Text

object GUI extends JFXApp {
  stage = new PrimaryStage {
    title = "WizardChess"

    scene = new Scene {




      /*val menuBar: MenuBar = new MenuBar { //edit
        useSystemMenuBar = true
        minWidth = 100
        val menuList: Menu = new Menu("Edit") {
          items.add(new MenuItem("Exit"))
          items.add(new MenuItem("Redo"))
          items.add(new MenuItem("Save"))
        }
        menus.add(menuList)
      }*/

      val rootPane: BorderPane = new BorderPane { //layout - innen drinnen aufgesplittet in top center left right and bottom

        //top = menuBar
        //left = new Button("djkfhsdajklhjsdfh" )
        //right = new Button("djkfhsdajklhjsdfh" )
        center = new Text {
          text = "Welcome to WizardChess - this is your game!"
          style = "-fx-font-size: 48pt"
          fill = Black
        }
        bottom = new Button {
          text = "Let's go"
          fill = GreenYellow
          onAction = { _ => println("changing to the next window to choose the game option") }
        }

        top = new Button {
          text = "Rules"
        }
      }
      root = rootPane
    }
  }
}


