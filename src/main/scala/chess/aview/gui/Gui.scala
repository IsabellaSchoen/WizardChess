package chess.aview.gui

import java.util

import chess.control.controllerComponent.ControllerTrait
import chess.model.RulesAll
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

import scala.collection.{immutable, mutable}
import scala.io.Source

class Gui(controller: ControllerTrait) extends JFXApp with Observer {

  controller.add(this)

  var x: Int = -1
  var y: Int = -1

  val HEIGHT = 720
  val WIDTH = 1400

  var markiert = new Array[Boolean](64)

  var help = false

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
            "-fx-background-color: rgba(0, 0, 0, 0.4);" +
            "-fx-background-radius: 20px;" +
            "-fx-border-radius: 20px;" +
            "-fx-text-fill: white;"
          style <== when(hover) choose stdStyle + "-fx-border-color: white;" otherwise stdStyle

          prefHeight = 80
          prefWidth = 200
          onAction = { _ => {
            println("changing to the next window to choose the game option")
            stage.setWidth(1920)
            stage.setHeight(1080)
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
            "-fx-background-color: rgba(0, 0, 0, 0.4);" +
            "-fx-background-radius: 20px;" +
            "-fx-border-radius: 20px;" +
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

        val helpButton: RadioButton = new RadioButton("show possible moves") {
          selected = help
          style = "-fx-padding: 25px; -fx-font-size: 20pt; -fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0.4); -fx-background-radius: 20px;"
          onAction = { _ => {
            help = this.selected.value
            println(help)
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
            "-fx-background-color: rgba(0, 0, 0, 0.4);" +
            "-fx-background-radius: 20px;" +
            "-fx-border-radius: 20px;" +
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
          children = List(buttonGo, buttonLoad, helpButton, buttonRules)
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

      val devPane: BorderPane = new BorderPane {
        pickOnBounds = false
        right = new VBox {
          children = Seq(
            new Button {
              text = "devMode"
              val stdStyle: String = "-fx-font-size: 10pt;" +
                "-fx-background-color: transparent;"
              style <== when(hover) choose stdStyle + "-fx-border-color: black;" otherwise stdStyle
              prefHeight = 50
              prefWidth = 150
              onAction = { _ => {
                if (controller.getState() != 0) {
                  text.set("normal mode")
                  println("entering devMode")
                  controller.setState(0)
                } else {
                  text = "devMode"
                  println("exiting devMode")
                  controller.setState(1)
                }
              }
              }
            },
            new Button("Home") {
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
                println("entering main menu")
                initialize()
              }
              }
            },
            new Button("Save game") {
              val stdStyle: String = "-fx-font-size: 20px;" +
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
                println("saving game")
                controller.save()
              }
              }
            },
            new Button("Load game") {
              val stdStyle: String = "-fx-font-size: 20px;" +
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
                println("loading game")
                controller.load()
              }
              }
            }
          )
        }
      }

      val statePane: BorderPane = new BorderPane() {
        val stateStyle = "-fx-font-size: 25pt"
        top = controller.getState() match {
          case 1 => new Label("Weiß ist am Zug") {
            style = stateStyle
          }
          case 2 => new Label("Schwarz ist am Zug") {
            style = stateStyle
          }
          case 0 => new Label("devMode") {
            style = stateStyle
          }
        }
      }

      val grid = new GridPane
      markiert = new Array[Boolean](64)
      var fig = new GridPane

      grid.setAlignment(Pos.Center)
      fig.setAlignment(Pos.Center)

      for (i <- 0 to 7) {
        for (j <- 0 to 7) {
          grid.add(new ChessButton { //Buttons eingefügt
            style = "-fx-background-color: transparent; -fx-background-radius: 50%"
            onAction = { _ => {
              col(this)

              for (x <- 0 until 8) {
                for (y <- 0 until 8) {
                  if ((controller.board.check(i, j, x, y) && controller.board.Matrix(x)(y).isEmpty)
                    || (RulesAll.hit(controller.board, i, j, x, y) && !controller.board.Matrix(x)(y).isEmpty &&
                    !controller.board.Matrix(i)(j).figure.col.equals(controller.board.Matrix(x)(y).figure.col)
                    && ((controller.board.check(i, j, x - 1, y - 1) || controller.board.check(i, j, x - 1, y + 1)
                    || controller.board.check(i, j, x + 1, y - 1) || controller.board.check(i, j, x + 1, y + 1)
                    || controller.board.check(i, j, x - 1, y) || controller.board.check(i, j, x + 1, y)
                    || controller.board.check(i, j, x, y - 1) || controller.board.check(i, j, x, y + 1))))) {
                    val loc = (x * 8) + y
                    val tmp = grid.children.get(loc)
                    if (help)
                      mark(tmp)
                    markiert(loc) = true
                  }
                }
              }

              click(i, j)
            }
            }
            prefWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            prefHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 8
            onMouseEntered = e => {
              style = "-fx-background-color: rgba(0, 255, 0, 0.3); " +
                "-fx-outer-border: rgba(0, 255, 0, 0.3); " +
                "-fx-inner-border: rgba(0, 255, 0, 0.3); " +
                "-fx-body-color: rgba(0, 255, 0, 0.3); " +
                "-fx-effect: dropshadow(gaussian, blue, 50, 0, 0, 0); "
            }
            onMouseExited = e => {
              if (selected)
                col(this)
              else if (markiert((i * 8) + j) && help)
                mark(this)
              else
                style = "-fx-background-color: transparent;"
            }
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
            fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 9
            fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 9
          }, i, j)

          graveBlack.add(new ImageView("file:" + controller.getFig(controller.graveyardBlack, i, j) + ".png") {
            fitWidth = (stage.getHeight - (stage.getHeight / 7.2)) / 9
            fitHeight = (stage.getHeight - (stage.getHeight / 7.2)) / 9
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

      stackPane.getChildren.addAll(background, statePane, graveWhitePane, graveBlackPane, tmpfig, tmp, devPane)
      root = stackPane
    }

    //    stage.setFullScreen(true)
  }


  def click(i: Integer, j: Integer): Unit = {
    if (x != -1 && markiert((i * 8) + j)) {
      controller.move((x + 'A').toChar, (y + '1').toChar, (i + 'A').toChar, (j + '1').toChar)
      x = -1
      y = -1
    } else if (x == -1 && !controller.getFig(controller.board, i, j).equals("_black")) {
      x = i
      y = j
    } else if (x != -1 && !markiert((i * 8) + j)) {
      x = -1
      y = -1
      play()
    }
  }

  def col(b: ChessButton): Unit = {
    b.selected = true
    b.style = "-fx-background-color: rgba(0, 255, 0, 0.3); " +
      "-fx-background-radius: 25%;" +
      "-fx-border-radius: 25%; " +
      "-fx-border-color: rgba(0, 255, 0, 0.4);" +
      "-fx-border-width: 10pt;" +
      "-fx-effect: dropshadow(gaussian, green, 50, 0, 0, 0);"
  }

  def mark(b: javafx.scene.Node): Unit = {
    b.setStyle("-fx-background-color: rgba(255, 0, 0, 0.3); " +
      "-fx-background-radius: 25%;" +
      "-fx-border-radius: 25%; " +
      "-fx-border-color: rgba(255, 0, 0, 0.4);" +
      "-fx-border-width: 10pt;" +
      "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);")
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

class ChessButton extends Button {
  var selected = false
}