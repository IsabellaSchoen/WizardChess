package chess.aview.gui

import chess.control.Controller
import chess.util.Observer
import scalafx.Includes.when
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Pos
import scalafx.scene.control._
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{BorderPane, GridPane, StackPane, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalafx.scene.{Node, Scene}

import scala.io.Source

class Gui(controller: Controller) extends JFXApp with Observer {

  controller.add(this)

  var x: Int = -1
  var y: Int = -1

  val HEIGHT = 720
  val WIDTH = 1400

  stage = new PrimaryStage {
    title = "WizardChess"
    width = WIDTH
    height = HEIGHT
  }

  initialize()

  def initialize(): Unit = {
    stage.scene = new Scene {


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
  }

  def play(): Unit = {
    //stage.setFullScreen(true)

    stage.scene = new Scene {
      val stackPane: StackPane = new StackPane
      val background = new ImageView("file:schachbrett.jpg")
      background.setFitHeight(stage.getHeight)
      background.setFitWidth(stage.getHeight)

      val figures: GridPane = new GridPane {
        alignment = Pos.Center
      }

      /*for (i <- 0 to 7) {
        for (j <- 0 to 7) {
          figures.add(new ImageView() {
            prefWidth = (stage.getHeight - 100) / 8
            prefHeight = (stage.getHeight - 100) / 8
          }, i, j)
        }
      }*/

      val grid = new GridPane
      var fig = new GridPane

      grid.setAlignment(Pos.Center)
      fig.setAlignment(Pos.Center)

      for (i <- 0 to 7) {
        for (j <- 0 to 7) {
          grid.add(new Button {
            style = "-fx-background-color: transparent;"
            onAction = { _ => click(i, j) }
            prefWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            prefHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
          }, i, j)

          fig.add(new ImageView("file:" + controller.getFig(i, j) + ".png") {
            fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
          }, i, j)
        }
      }

      val tmpfig: BorderPane = new BorderPane {
        center = fig
      }

      val tmp: BorderPane = new BorderPane {
        center = grid
      }

      stackPane.getChildren.addAll(background, tmpfig, tmp)
      root = stackPane
    }

    stage.setFullScreen(true)
  }


  def click(i: Integer, j: Integer): Unit = {
    print(i + " " + j + "\n")
    if (x != -1) {
      println("move" + (x + 'A').toChar + (y + '1').toChar + "to" + (i + 'A').toChar + (j + '1').toChar)
      controller.move((x + 'A').toChar, (y + '1').toChar, (i + 'A').toChar, (j + '1').toChar)
      x = -1
      y = -1
    } else {
      x = i
      y = j
    }
  }


  def rule() = {
    stage.scene = new Scene {
      val stackPane: StackPane = new StackPane
      val tmpPane = new BorderPane {
        top = new Button("Go Back") {
          val stdStyle = "-fx-font-size: 25px;" +
            "-fx-background-radius: 5em;" +
            "-fx-min-width: 30px;" +
            "-fx-min-height: 30px;" +
            "-fx-max-width: 120px;" +
            "-fx-max-height: 60px;" +
            "-fx-padding:5;" +
            "-fx-background-color: transparent;" +
            "-fx-text-fill: black;"
          style <== when(hover) choose stdStyle + "-fx-border-color: black;" otherwise stdStyle
          prefHeight = 100
          prefWidth = 300
          background = null
          onAction = { _ => {
            println("Going back")
            initialize()
          }
          }
        }
      }

      val rect = new Rectangle {
        width = 950
        height = 800
        fill = FireBrick
        opacity = 0.5
      }

      rect.setArcHeight(100)
      rect.setArcWidth(100)

      stackPane.getChildren.addAll(tmpPane, rect,
        new Text(Source.fromFile("Rules.txt").mkString) {
          style = "-fx-font-size: 15px"
        })
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


  override def update(): Unit = {
    /*fig = new GridPane
    fig.setAlignment(Pos.Center)

    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        fig.add(new ImageView("file:" + controller.getFig(i, j) + ".png") {
          fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
          fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
        }, i, j)
      }
    }*/

    play()
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