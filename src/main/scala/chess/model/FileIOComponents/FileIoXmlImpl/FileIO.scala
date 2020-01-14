package chess.model.FileIOComponents.FileIoXmlImpl

import java.io.{File, PrintWriter}
import chess.WizardChessModule
import chess.model.BoardCreator
import chess.model.FileIOComponents.FileIOInterface
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import scala.util.Try
import scala.xml.{Elem, PrettyPrinter}



class FileIO extends FileIOInterface {


  //loading the board - this is freaking me out!
  override def load: Try[Option[BoardCreator]] = {
    var boardOption: Option[BoardCreator] = None

    Try {
      val file: Elem = scala.xml.XML.loadFile("board.xml")
      val boardMatch = (file \\ "boardcreator" \ "@boardMatch").text.toInt

      val injector = Guice.createInjector(new WizardChessModule)
      boardMatch match {
        case 8 => boardOption = Some(injector.instance[BoardCreator](Names.named("normal")))
        case _ =>
      }


      val cellMatch = (file \\ "cell")
      boardOption match {
        case Some(cell) => {
          var _cell = cell
          for (cell <- cellMatch) {
            val row: Int = (cell \ "@row").text.toInt
            val col: Int = (cell \ "@col").text.toInt
            val showFigures = (cell \ "@showFigures").text.toString
          }
          boardOption = Some(_cell)
        }
        case None =>
      }
      boardOption
    }
  }

  // den Spielstand speicher - this is freaking me out! Why? Just, WHY?!
  def save(board: BoardCreator): Unit = {
    val pw = new PrintWriter(new File("boardcreater.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(boardToXml(board)
    pw.write(xml)
    pw.close
  }


  //Board speichern - done
  def boardToXml(board: BoardCreator) = {
    <board size = {board.size.toString}>
      {
      for {
        row <- 0 until board.size
        col <- 0 until board.size
      } cellToXml(board, row, col)
      }
    </board>
  }


  //Zelle speicher - missing figures and their colors
  def cellToXml(board: BoardCreator, row: Int, col: Int) = {
    <cell row ={row.toString} col={col.toString} >
    </cell>
  }

}

