package chess.model.FileIOComponents.FileIoXmlImpl

import java.io.{File, PrintWriter}
import chess.model.BoardCreator
import chess.model.FileIOComponents.FileIOInterface
import chess.model.boardComponent.BoardTrait
import scala.xml.{Elem, PrettyPrinter}


class FileIO extends FileIOInterface {

  //loading the board - this is freaking me out!
  override def load: BoardTrait = {
    val file: Elem = scala.xml.XML.loadFile("board.xml")


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
      <figure type={board.Matrix(x)(y).figure.getClass} color={board.Matrix(x)(y).figure.col}>
      </figure>
    </cell>
  }

}

