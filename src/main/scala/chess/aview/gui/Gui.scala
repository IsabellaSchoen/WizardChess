package chess.aview.gui

import chess.control.controllerComponent.ControllerTrait
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

class Gui(controller: ControllerTrait) extends JFXApp with Observer {

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


      val borderPane: BorderPane = new BorderPane {
        var title1: Node = new Text {
          text = "Welcome to WizardChess"
          style = "-fx-font-size: 48pt"
          fill = FireBrick
        }

        top = new BorderPane {
          prefHeight = 200
          center = title1
        }

        val buttonGo: Button = new Button {
          text = "Let's go"
          val stdStyle: String = "-fx-font-size: 40px;" +
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
            stage.setHeight(1080)
            stage.setWidth(1920)
            play()
          }
          }
        }

        val buttonLoad: Button = new Button {
          text = "Load game"
          val stdStyle: String = "-fx-font-size: 40px;" +
            "-fx-background-radius: 5em;" +
            "-fx-min-width: 30px;" +
            "-fx-min-height: 30px;" +
            "-fx-max-width: 240px;" +
            "-fx-max-height: 100px;" +
            "-fx-padding:5;" +
            "-fx-background-color: transparent;" +
            "-fx-text-fill: white;"
          style <== when(hover) choose stdStyle + "-fx-border-color: white;" otherwise stdStyle

          prefHeight = 80
          prefWidth = 200
          onAction = { _ => {
            println("loading game")
            controller.load()
            stage.setHeight(1080)
            stage.setWidth(1920)
            play()
          }
          }
        }


        val buttonRules: Button = new Button {
          text = "Rules"
          val stdStyle: String = "-fx-font-size: 35px;" +
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
          children = List(buttonGo, buttonLoad, buttonRules)
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

  override def stopApp(): Unit = {
    controller.save()
    stage.close()
    System.exit(0)
  }

  def play(): Unit = {
    //stage.setFullScreen(true)

    stage.scene = new Scene {

      val stackPane: StackPane = new StackPane
      val background = new ImageView("file:schachbrett.jpg")
      background.setFitHeight(stage.getHeight)
      background.setFitWidth(stage.getHeight)

      val statePane: BorderPane = new BorderPane() {
        val stateStyle = "-fx-font-size: 25pt"
        top = controller.getState() match {
          case 1 => new Label("Weiß ist am Zug") {
            style = stateStyle
          }
          case 2 => new Label("Schwarz ist am Zug") {
            style = stateStyle
          }
        }
      }

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

          fig.add(new ImageView("file:" + controller.getFig(controller.board, i, j) + ".png") {
            fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
          }, i, j)
        }
      }

      val graveWhite = new GridPane
      val graveBlack = new GridPane

      for (i <- 0 until 4) {
        for (j <- 0 until 4) {
          graveWhite.add(new ImageView("file:" + controller.getFig(controller.graveyardWhite, i, j) + ".png") {
            fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
          }, i, j)

          graveBlack.add(new ImageView("file:" + controller.getFig(controller.graveyardBlack, i, j) + ".png") {
            fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
          }, i, j)
        }
      }

      graveWhite.setAlignment(Pos.CenterLeft)
      graveBlack.setAlignment(Pos.CenterRight)

      val graveWhitePane: BorderPane = new BorderPane {
        center = graveWhite
      }

      val graveBlackPane: BorderPane = new BorderPane {
        center = graveBlack
      }

      val tmpfig: BorderPane = new BorderPane {
        center = fig
      }

      val tmp: BorderPane = new BorderPane {
        center = grid
      }

      stackPane.getChildren.addAll(background, statePane, graveWhitePane, graveBlackPane, tmpfig, tmp)
      root = stackPane
    }

    //    stage.setFullScreen(true)
  }


  def click(i: Integer, j: Integer): Unit = {
    print(i + " " + j + "\n")
    if (x != -1) {
      println("move" + (x + 'A').toChar + (y + '1').toChar + "to" + (i + 'A').toChar + (j + '1').toChar)
      controller.move((x + 'A').toChar, (y + '1').toChar, (i + 'A').toChar, (j + '1').toChar)
      x = -1
      y = -1
    } else if (!controller.getFig(controller.board, i, j).equals("_black")) {
      x = i
      y = j
    }
  }


  def rule(): Unit = {
    stage.scene = new Scene {
      val stackPane: StackPane = new StackPane
      val tmpPane: BorderPane = new BorderPane {
        top = new Button("Go Back") {
          val stdStyle: String = "-fx-font-size: 25px;" +
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

      val rect: Rectangle = new Rectangle {
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