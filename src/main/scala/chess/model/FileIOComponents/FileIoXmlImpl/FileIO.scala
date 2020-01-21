package chess.model.FileIOComponents.FileIoXmlImpl

import java.io.{File, PrintWriter}

import chess.WizardChessModule
import chess.model.FileIOComponents.FileIOInterface
import chess.model.boardComponent.BoardTrait
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import scala.xml.{Elem, PrettyPrinter}


class FileIO extends FileIOInterface {

  override def load: BoardTrait = {
    var board: BoardTrait = null
    val file: Elem = scala.xml.XML.loadFile("board.xml")

    val sizeAttr = (file \\ "board" \ "@size").text.toInt

    val injector = Guice.createInjector(new WizardChessModule)
    sizeAttr match {
      case 8 => board = injector.instance[BoardTrait](Names.named("normal"))
      case 16 => board = injector.instance[BoardTrait](Names.named("twice"))
      case 32 => board = injector.instance[BoardTrait](Names.named("triple"))
    }

    val cellNodes = (file \\ "cell")
    for(cell <- cellNodes) {
      val x = Integer.parseInt((cell \ "@x").text)
      val y = (cell \ "@y").text.charAt(0)
      val fig = (cell \\ "fig" \ "@type").text
      val f = fig match {
        case "class chess.model.None" => ' '
        case "class chess.model.Pawn" => 'P'
        case "class chess.model.King" => 'K'
        case "class chess.model.Bishop" => 'B'
        case "class chess.model.Rook" => 'R'
        case "class chess.model.Queen" => 'Q'
        case "class chess.model.Horse" => 'H'
      }
      val color = (cell \\ "fig" \ "@color").text.charAt(0)
      board = board.put((x + 'A').toChar, (y + 1).toChar, f, color)
//      println((x + 'A').toChar + " " + y + " " + f + " " + color)
    }
    board
  }


  // den Spielstand speicher - this is freaking me out! Why? Just, WHY?!
  def save(board: BoardTrait): Unit = {
    val pw = new PrintWriter(new File("board.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(boardToXml(board))
    pw.write(xml)
    pw.close()
  }


  //Board speichern - done
  def boardToXml(board: BoardTrait): Elem = {
    <board size={board.size.toString}>
      {for {
      x <- 0 until board.size
      y <- 0 until board.size
    } yield cellToXml(board, x, y)}
    </board>
  }


  //Zelle speicher - missing figures and their colors
  def cellToXml(board: BoardTrait, x: Int, y: Int): Elem = {
    <cell x={x.toString} y={y.toString}>
      <fig type={board.Matrix(x)(y).figure.getClass.toString} color={board.Matrix(x)(y).figure.col.toString}>
      </fig>
    </cell>
  }

}

