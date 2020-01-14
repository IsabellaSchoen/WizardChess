package chess.model.FileIOComponents.FileIoXmlImpl

import java.io.{File, PrintWriter}

import chess.model.FileIOComponents.FileIOInterface
import chess.model.boardComponent.BoardTrait
import chess.model.{BoardCreator, Cell}

import scala.xml.{Elem, PrettyPrinter}


class FileIO extends FileIOInterface {


  //
  override def load: BoardCreator = {
    val file: Elem = scala.xml.XML.loadFile("board.xml")
    loadToXml(file)
  }

  def loadToXml(elem: Elem): BoardCreator = {
    val  = (Elem \\ "boardcreator" \ "@board").text.toInt
  }

  // den Spielstand speicher - done
  def save(board: BoardTrait): Unit = {
    val pw = new PrintWriter(new File("WizardChess.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(boardToXml(board))
    pw.write(xml)
    pw.close
  }



  //Board speichern - done
  def boardToXml(board: BoardTrait, col: Int, row: Int) = {
    <board size = {board.size.toString}>
      {
      for {
        row <- 0 until board.size
        col <- 0 until board.size
      } boardToXml(board, col, row)
      }
    </board>
  }


  //Zelle speicher - done
  def cellToXml(cell: Cell, row: Int, col: Int) = {
    <cell row ={row.toString} col={col.toString} Figure = {cell.figure.toString} Figure = {cell.figure.col}>
    </cell>
  }

}

