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
      case _ =>
    }

    val cellNodes = (file \\ "cell")
    for(cell <- cellNodes) {
      val x: Char = (cell \ "@x").text.charAt(0)
      val y: Char = (cell \ "@y").text.charAt(0)
      val fig: Char = (cell \\ "figure" \ "@type").text.charAt(0)
      val color: Char = (cell \\ "figure" \ "@col").text.charAt(0)
      board = board.put(x, y, fig, color)
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
    } cellToXml(board, x, y)}
    </board>
  }


  //Zelle speicher - missing figures and their colors
  def cellToXml(board: BoardTrait, x: Int, y: Int): Elem = {
    <cell x={x.toString} y={y.toString}>
      <!--fig type={board.Matrix(x)(y).figure.getClass} color={board.Matrix(x)(y).figure.col}>
      </fig-->
    </cell>
  }

}

