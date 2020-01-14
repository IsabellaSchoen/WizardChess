package chess.control.controllerComponent.controllerBaseImpl

import chess.WizardChessModule
import chess.control.controllerComponent.ControllerTrait
import chess.model.FileIOComponents.FileIOInterface
import chess.model._
import chess.model.boardComponent.BoardTrait
import chess.util.UndoManager
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._



import chess.util.Observable
import chess.util.{Observable, UndoManager}

class Controller @Inject() (var board: BoardTrait) extends ControllerTrait {
  private var state: Int = 1
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new WizardChessModule)
  val undoManager = new UndoManager


  //FileIO
  val fileIo = injector.instance[FileIOInterface]


  def createNewBoard(): Unit = {
    board.size match {
      case 8 => board = injector.instance[BoardTrait](Names.named("normal"))
      case 16 => board = injector.instance[BoardTrait](Names.named("twice"))
      case 32 => board = injector.instance[BoardTrait](Names.named("triple"))
      case _ =>
    }
  }


  override def put(x: Char, y: Char, f: Char, c: Char): Unit = {
    board = board.put(x, y, f, c)
    notifyObservers()
  }

  override def empty(): Unit = {
    board = BoardCreator(8).create
    notifyObservers()
  }

  override def boardToString: String = board.toString

  override def setState(state: Int): Unit = {
    this.state = state
    notifyObservers()
  }

  override def move(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    undoManager.doStep(new MoveCommand(this, x1, y1, x2, y2))
  }

  override def moveAll(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.move(x1, y1, x2, y2)
    notifyObservers()
  }

  def undo = {
    undoManager.undoStep
    notifyObservers()
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers()
  }

  /**
    * der erste Player hat die weisse Farbe
    */
  override def moveOne(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.moveWhite(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  /**
    * der zweite Player hat die schwarze Farbe
    */
  override def moveTwo(x1: Char, y1: Char, x2: Char, y2: Char) : Unit = {
    board = board.moveBlack(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  def back(x1: Char, y1: Char, x2: Char, y2: Char) = {
    board = board.back(x1, y1, x2, y2)
    state match {
      case 1 => state = 2
      case 2 => state = 1
    }
    notifyObservers()
  }

  override def create(): Unit = {
    board = BoardCreator(8).init(BoardCreator(8).create)
    notifyObservers()
  }

  override def getFig(i: Int, j: Int): String = {
    var tmp = board.Matrix(i)(j).figure.toString match {
      case "pawn" => "pawn"
      case "king" => "king"
      case "queen" => "queen"
      case "rook" => "rook"
      case "horse" => "horse"
      case "bishop" => "bishop"
      case _ => ""
    }
    tmp += "_"
    board.Matrix(i)(j).figure.col match {
      case 'B' => tmp += "black"
      case 'W' => tmp += "white"
    }
    tmp
  }

}
