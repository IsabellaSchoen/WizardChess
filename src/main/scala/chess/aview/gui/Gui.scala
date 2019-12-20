package chess.aview.gui

import scalafx.Includes.when
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Pos
import scalafx.scene.control._
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{BorderPane, StackPane, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.text.Text
import scalafx.scene.{Node, Scene}

object Gui extends JFXApp {

  stage = new PrimaryStage {
    title = "WizardChess"
    width = 1400
    height = 720
    scene = new Scene {


      val borderPane = new BorderPane {
        var title1: Node = new Text {
          text = "Welcome to WizardChess"
          style = "-fx-font-size: 48pt"
          fill = FireBrick
        }

        top = new BorderPane {
          prefHeight = 200
          center = title1
        }

        val buttonGo = new Button {
          text = "Let's go"
          val stdStyle = "-fx-font-size: 40px;" +
            "-fx-background-radius: 5em;" +
            "-fx-min-width: 30px;" +
            "-fx-min-height: 30px;" +
            "-fx-max-width: 180px;" +
            "-fx-max-height: 100px;" +
            "-fx-padding:5;" +
            "-fx-background-color: transparent;" +
            "-fx-text-fill: white;"
          style <== when(hover) choose stdStyle + "-fx-border-color: white;" otherwise stdStyle

          prefHeight = 80
          prefWidth = 200
          onAction = { _ => {
            println("changing to the next window to choose the game option")
            play()
          }
          }
        }


        val buttonRules = new Button {
          text = "Rules"
          val stdStyle = "-fx-font-size: 35px;" +
            "-fx-background-radius: 5em;" +
            "-fx-min-width: 30px;" +
            "-fx-min-height: 60px;" +
            "-fx-max-width: 150px;" +
            "-fx-max-height: 100px;" +
            "-fx-padding:5;" +
            "-fx-background-color: transparent;" +
            "-fx-text-fill: white;"
          style <== when(hover) choose stdStyle + "-fx-border-color: white;" otherwise stdStyle

          prefHeight = 50
          prefWidth = 150
          onAction = { _ => {
            println("chess's rules")
            rule()
          }
          }
        }

        val white = new RadioButton("White Colour")
        white.layoutX = 30
        white.layoutY = 100
        val black = new RadioButton("Black Colour")
        black.layoutX = 30
        black.layoutY = 140
        val wizard = new RadioButton("Wizard Chess")
        wizard.layoutX = 30
        wizard.layoutY = 100
        val normal = new RadioButton("Chess")
        normal.layoutX = 30
        normal.layoutY = 140

        center = new VBox {
          alignment = Pos.Center
          children = List(buttonGo, buttonRules)
        }

        /*val borderpane: BorderPane = new BorderPane() {
        top = new BorderPane() {
          val box = new VBox {
            alignment = Pos.Center
            children = List(wizard, normal)
          }
        }
      }
        bottom = new VBox {
          alignment = Pos.Center
          children = List(white, black)
        }*/


      }

      val stackPane: StackPane = new StackPane
      val background = new ImageView("file:chessbg.png")
      stackPane.getChildren.addAll(background, borderPane)
      root = stackPane
    }

    def play(): Unit = {
      stage.scene = new Scene {

        val stackPane: StackPane = new StackPane
        val background = new ImageView("file:chessbg.png")
        stackPane.getChildren.addAll(background)
        root = stackPane
      }
    }


    def rule() = {
      stage.scene = new Scene {
        val stackPane: StackPane = new StackPane
            val button = new Button("Go Back") {
              style = "-fx-font-size: 28px"
              prefHeight = 100
              prefWidth = 300
              background = null
              onAction = { _ => {
                println("Going back")
                stage.scene
              }
              }
            }
        stackPane.getChildren.addAll(button)
        root = stackPane
            }
    }




      /*def createMenuBar = {

        val menuBar: MenuBar = new MenuBar {
          useSystemMenuBar = true
          minWidth = 100
          val menuList: Menu = new Menu("Edit") {
            items.add(new MenuItem("Exit"))
            items.add(new MenuItem("Redo"))
            items.add(new MenuItem("Save"))
          }
          menus.add(menuList)
        }
      }*/
    }
  }



// for the chess game later
//        Seq.fill(2)(new Button {
//          text = "Let's go"
//          style = "-fx-font-size: 28px"
//          prefHeight = 100
//          prefWidth = 300
//          onAction = { _ => {
//            println("changing to the next window to choose the game option")
//            play()
//          }
//          }
//          background = null
//        })