package chess.model.FileIOComponents.FileIoXmlImpl

import chess.model.BoardCreator
import chess.model.FileIOComponents.FileIOInterface
import chess.model.boardComponent.BoardTrait

class FileIO extends FileIOInterface {


  override def load: BoardTrait = {
    var board: BoardTrait = null
    val file = scala.xml.XML.loadFile("boardtrait.xml")
  }

  override def save(board: BoardTrait): Unit = ???




  def boardToXml(board: BoardCreator) = {
    <board size = {board.size.toString}>
      {}
    </board>
  }


}

