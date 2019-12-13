package chess.aview.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.text.Text

object ScalaFXHelloWorld extends JFXApp {

  stage = new PrimaryStage {
    title = "WizardChess - This is your Game!"
    scene = new Scene {
      fill = White
      content = new HBox {
        padding = Insets(20)
        children = Seq(
          new Text {
            text = "Let's go"
            style = "-fx-font-size: 48pt"
          }
        )
      }
    }
  }
}



import java.beans.EventHandler

import javafx.event
import javafx.scene.layout.GridPane
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Node, Scene}
import scalafx.scene.control._
import scalafx.scene.input._
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{BorderPane, VBox}
import scalafx.geometry.Insets
import scalafx.scene.control.Alert.AlertType

import scala.swing.{Action, Orientation}
import scala.swing.event.ActionEvent

object GUI extends JFXApp {
  stage = new PrimaryStage {
    title = "MenuBar Test"
    scene = new Scene(300, 300) {


      val menuBar: MenuBar = new MenuBar {
        useSystemMenuBar = true
        minWidth = 100
        val menuList: Menu = new Menu("Edit") {
          items.add(new MenuItem("Undo"))
          items.add(new MenuItem("Redo"))
          items.add(new MenuItem("Save"))
        }
        menus.add(menuList)
      }

      val rootPane: BorderPane = new BorderPane {
        top = menuBar
        center = BoardPanel.newBoardView()
        bottom = CardPanel.newCardViews()
      }

      root = rootPane
    }
  }
}


//val button = new Button("Click Me!")
//button.layoutX = 100
//button.layoutY = 100
//val rect = Rectangle(400,200,100,150)
//rect.fill = Color.Azure
//
//var enemies = List(Circle(10, 10, 10))
//val player = Circle(300, 300, 20)
//player.fill = Color.Green
//content = List(enemies.head, player,button,rect)
//
//
//
//var lastTime = 0L
//val enemySpeed = 20
//val playerSpeed = 25
//
//
//val timer = AnimationTimer(t => {
//if (lastTime > 0) {
//val delta = (t - lastTime) / 1e9
//for (e <- enemies) {
//val dx = player.centerX.value - e.centerX.value
//val dy = player.centerY.value - e.centerY.value
//val dist = math.sqrt(dx * dx + dy * dy)
//e.centerX = e.centerX.value + dx / dist * enemySpeed * delta
//
//e.centerY = e.centerY.value + dy / dist * enemySpeed * delta
//}
//}
//lastTime = t
//})
//timer.start