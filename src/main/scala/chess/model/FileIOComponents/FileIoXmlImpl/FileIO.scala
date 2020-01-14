package chess.model.FileIOComponents.FileIoXmlImpl

import java.io.{File, PrintWriter}

import chess.model.FileIOComponents.FileIOInterface
import chess.model.boardComponent.BoardTrait
import chess.model.{BoardCreator, Cell}
import scala.xml.PrettyPrinter




class FileIO extends FileIOInterface {


  override def load: BoardCreator = {
    var board: BoardCreator = null
    val file = scala.xml.XML.loadFile("boardt.xml")
  }


  // den Spielstand speicher - done
  def save(board: BoardTrait): Unit = {
    val pw = new PrintWriter(new File("WizardChess.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(boardToXml(board))
    pw.write(xml)
    pw.close
  }



  //da Board speichern - done
  def boardToXml(board: BoardTrait) = {
    <board size = {board.size.toString}>
      {
      for {
        row <- 0 until board.size
        col <- 0 until board.size
      } boardToXml(board, row, col)
      }
    </board>
  }


  def cellToXml(cell: Cell) = {
    <cell row ={row.toString} col={col.toString} given={grid.cell(row,col).given.toString} isHighlighted={grid.isHighlighted(row,col).toString} showCandidates={grid.cell(row, col).showCandidates.toString}>

    {grid.cell(row,col).value}

    </cell>
  }




}

