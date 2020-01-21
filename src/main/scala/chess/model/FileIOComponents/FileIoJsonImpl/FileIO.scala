package chess.model.FileIOComponents.FileIoJsonImpl

import java.io.PrintWriter

import chess.WizardChessModule
import chess.model.FileIOComponents.FileIOInterface
import chess.model.boardComponent.BoardTrait
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.{JsNumber, JsValue, Json}

import scala.io.Source

class FileIO extends FileIOInterface{
  override def load: BoardTrait = {
    var board: BoardTrait = null
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "board" \ "size").get.toString.toInt

    val injector = Guice.createInjector(new WizardChessModule)
    size match {
      case 8 => board = injector.instance[BoardTrait](Names.named("normal"))
      case 16 => board = injector.instance[BoardTrait](Names.named("twice"))
      case 32 => board = injector.instance[BoardTrait](Names.named("triple"))
    }

    for (index <- 0 until size * size) {
      val x = (json \\ "x")(index).as[Int]
      val y = (json \\ "y")(index).as[Int]
      val cell = (json \\ "cell")(0)
      val fig = (cell(index) \\ "type").toString().drop(6).dropRight(2) match {
        case "class chess.model.None" => ' '
        case "class chess.model.Pawn" => 'P'
        case "class chess.model.King" => 'K'
        case "class chess.model.Bishop" => 'B'
        case "class chess.model.Rook" => 'R'
        case "class chess.model.Queen" => 'Q'
        case "class chess.model.Horse" => 'H'
      }
      val col = (cell(index) \\ "c").toString().drop(6).dropRight(2) match {
        case "W" => 'W'
        case "B" => 'B'
      }
      board = board.put((x + 'A').toChar, (y + '1').toChar, fig, col)
    }
    board
  }

  override def save(board: BoardTrait): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.json"))
    pw.write(Json.prettyPrint(boardToJson(board)))
    pw.close
  }

  def boardToJson(board: BoardTrait) = {
    Json.obj(
      "board" -> Json.obj(
        "size" -> JsNumber(board.size),
        "cell" -> Json.toJson(
          for {
            x <- 0 until board.size;
            y <- 0 until board.size
          } yield {
            Json.obj(
              "x" -> x,
              "y" -> y,
              "type" -> board.Matrix(x)(y).figure.getClass.toString,
              "c" -> board.Matrix(x)(y).figure.col.toString
            )
          }
        )
      )
    )
  }
}
