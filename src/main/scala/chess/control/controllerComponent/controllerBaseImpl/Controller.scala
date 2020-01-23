package chess.control.controllerComponent.controllerBaseImpl

import chess.WizardChessModule
import chess.control.MoveCommand
import chess.control.controllerComponent.ControllerTrait
import chess.model.FileIOComponents.FileIOInterface
import chess.model.FileIOComponents.FileIoJsonImpl.FileIO
import chess.model._
import chess.model.boardComponent.BoardTrait
import chess.util.UndoManager
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._

class Controller @Inject()(var board: BoardTrait) extends ControllerTrait {
  private val io: FileIOInterface = new FileIO()
  private var state: Int = 1
  val injector: Injector = Guice.createInjector(new WizardChessModule)
  val undoManager = new UndoManager


  def createNewBoard(): Unit = {
    board.size match {
      case 8 => board = injector.instance[BoardTrait](Names.named("normal"))
      case 16 => board = injector.instance[BoardTrait](Names.named("twice"))
      case 32 => board = injector.instance[BoardTrait](Names.named("triple"))
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
    val tmp = board.Matrix(board.xi(x2))(board.yi(y2)).figure
    if (!board.Matrix(board.xi(x2))(board.yi(y2)).isEmpty && RulesAll.hit(board, board.xi(x1), board.yi(y1), board.xi(x1), board.yi(y1)) && !(x1.equals(x2) && y1.equals(y2)))
      kill(tmp.caption, tmp.col)
    undoManager.doStep(new MoveCommand(this, x1, y1, x2, y2))
  }

  override def moveAll(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.move(x1, y1, x2, y2)
    notifyObservers()
  }

  def undo(): Unit = {
    undoManager.undoStep
    notifyObservers()
  }

  def redo(): Unit = {
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
  override def moveTwo(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.moveBlack(x1, y1, x2, y2)
    state = board.state
    notifyObservers()
  }

  override def back(x1: Char, y1: Char, x2: Char, y2: Char): Unit = {
    board = board.move(x2, y2, x1, y1)
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

  override def getFig(b: BoardTrait, i: Int, j: Int): String = {
    var tmp = b.Matrix(i)(j).figure.toString match {
      case "pawn" => "pawn"
      case "king" => "king"
      case "queen" => "queen"
      case "rook" => "rook"
      case "horse" => "horse"
      case "bishop" => "bishop"
      case _ => ""
    }
    tmp += "_"
    b.Matrix(i)(j).figure.col match {
      case 'B' => tmp += "black"
      case 'W' => tmp += "white"
    }
    tmp
  }

  override def save(): Unit = {
    io.save(board)
    notifyObservers()
  }

  override def load(): Unit = {
    board = io.load
    notifyObservers()
  }

  override def getState(): Int = state

  var graveyardWhite: BoardTrait = BoardCreator(4).create

  var graveyardBlack: BoardTrait = BoardCreator(4).create

  def kill(f: Char, c: Char): Unit = {
    var killColor: (Char, Int, Int) => Boolean = c match {
      case 'B' => killBlack
      case 'W' => killWhite
    }
    for (i <- 0 until 4) {
      for (j <- 0 until 4) {
        if (killColor(f, i, j))
          return
      }
    }
  }

  def killBlack(f: Char, i: Int, j: Int): Boolean = {
    if (graveyardBlack.Matrix(i)(j).isEmpty) {
      graveyardBlack.Matrix(i)(j) = graveyardBlack.Matrix(i)(j).set(Figure.translate(f), 'B')
      println(graveyardBlack)
      return true
    }
    false
  }

  def killWhite(f: Char, i: Int, j: Int): Boolean = {
    if (graveyardWhite.Matrix(i)(j).isEmpty) {
      graveyardWhite.Matrix(i)(j) = graveyardWhite.Matrix(i)(j).set(Figure.translate(f), 'W')
      println(graveyardWhite)
      return true
    }
    false
  }
}
