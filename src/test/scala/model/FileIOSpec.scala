package model

import chess.model.BoardCreator
import chess.model.FileIOComponents.FileIoXmlImpl.{FileIO => XML}
import chess.model.FileIOComponents.FileIoJsonImpl.{FileIO => JSON}
import chess.model.boardComponent.BoardTrait
import chess.model.boardComponent.boardBaseImpl.Board
import org.scalatest.{Matchers, WordSpec}

class FileIOSpec extends WordSpec with Matchers {
  "An XML FileIO system" should {
    var io = new XML
    "be able to save to a file" in {
      var tmp = BoardCreator(8).create
      io.save(tmp) should be ()
    }
    "be able to load a board from file" in {
      io.load.isInstanceOf[BoardTrait] should be (true)
    }
  }

  "A JSON FileIO system" should {
    var io = new JSON
    "be able to save to a file" in {
      var tmp = BoardCreator(8).create
      io.save(tmp) should be ()
    }
    "be able to load a board from file" in {
      io.load.isInstanceOf[BoardTrait] should be (true)
    }
  }
}
