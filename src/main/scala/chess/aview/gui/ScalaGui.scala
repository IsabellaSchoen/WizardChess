package chess.aview.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Node, Scene}
import scalafx.scene.control._

import scalafx.scene.layout.{BorderPane, VBox}

object GUI extends JFXApp {
  stage = new PrimaryStage {
    title = "MenuBar Test"
    scene = new Scene(300, 300) {


      val menuBar: MenuBar = new MenuBar { //edit
        useSystemMenuBar = true
        minWidth = 100
        val menuList: Menu = new Menu("Edit") {
          items.add(new MenuItem("Undo"))
          items.add(new MenuItem("Redo"))
          items.add(new MenuItem("Save"))
        }
        menus.add(menuList)
      }

      val rootPane: BorderPane = new BorderPane { //layout - innen drinnen aufgesplittet in top center left right and bottom

        top = menuBar
        left = new Button("djkfhsdajklhjsdfh" )
        right = new Button("djkfhsdajklhjsdfh" )
        center = new TextArea("ssssss")
        bottom = new TextArea("hhhhh")

      }

      root = rootPane
    }
  }
}


