package chess.aview.gui

import java.awt.Panel

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.{Node, Scene}
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.text.Text

object GUI extends JFXApp {
  stage = new PrimaryStage {
    title = "WizardChess"
    width = 1920
    height = 1080
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
        top = new BorderPane {
          prefHeight = 200
          center = new Text {
            text = "Welcome to WizardChess - this is your game!"
            style = "-fx-font-size: 48pt"
            fill = Black
          }

        }

        val buttonGo = new Button {
          text = "Let's go"
          style = "-fx-font-size: 28px"
          prefHeight = 100
          prefWidth = 200
          fill = GreenYellow
          onAction = { _ => println("changing to the next window to choose the game option") }
        }

        val buttonRules = new Button() {
          text = "Rules"
          style = "-fx-font-size: 28px"
          prefHeight = 100
          prefWidth = 200
        }

        center = new VBox {
          alignment = Pos.Center
          children = List(buttonGo, buttonRules)
        }
      }
      root = rootPane
    }
  }
}