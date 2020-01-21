package model

import chess.model._
import chess.model.boardComponent.{Board, BoardTrait}
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers {
  "A board" should {
    "be a matrix" in {
      Board(8).Matrix.isInstanceOf[Array[Array[Cell]]] should be (true)
    }
    "have a string representation" in {
      Board(8).Matrix(0)(0) should be (null)
      Board(8).toString should be ("empty Board")
      val test: BoardTrait = BoardCreator(8).create
      test.toString should be (test.toString)
    }
    "have Cells" which {
      "have an x value" in {
        Board(8).xi('A') should be (0)
        Board(8).xi('a') should be (0)
        Board(8).xi('Z') should be (-1)
      }
      "have a y value" in {
        Board(8).yi('1') should be (0)
        Board(8).yi('Z') should be (-1)
      }
    }
    "be able to move figures" in {
      val t1: BoardTrait = BoardCreator(8).init(BoardCreator(8).create)
      val t2: BoardTrait = t1
      t1.move('Z', 'Z', 'Z', 'Z') should be (t2)
      t1.move('A', '2', 'B', '4') should be (t2)
      t1.move('A', '1', 'A', '2') should be (t2)
      t1.move('A', '2', 'A', '4').Matrix(0)(1).figure.isInstanceOf[None] should be (true)
      t1.Matrix(0)(3).figure.caption should be ('P')
    }
    "be of size 8 when beeing called without size parameter" in {
      new chess.model.boardComponent.boardBaseImpl.Board().size should be (8)
    }
    "be able to take a step back" in {
      var tmp = BoardCreator(8).init(BoardCreator(8).create)
      tmp.move('A', '8', 'A', '5').back('A', '8', 'A', '5').Matrix(0)(7).figure.isInstanceOf[None] should be (false)
      tmp.move('A', '7', 'A', '5').back('A', '7', 'A', '5').Matrix(0)(7).figure.isInstanceOf[None] should be (false)
      tmp.move('A', '7', 'A', '6').move('A', '6', 'A', '5').back('A', '6', 'A', '5').Matrix(0)(7).figure.isInstanceOf[None] should be (false)
      tmp.move('A', '1', 'A', '2').move('A', '2', 'A', '3').back('A', '2', 'A', '3').Matrix(0)(7).figure.isInstanceOf[None] should be (false)
    }
  }
  "Figures" when {
    "black" should {
      "not be moved when it's player one's turn" in {
        BoardCreator(8).init(BoardCreator(8).create).moveBlack('A', '7', 'A', 'Z').isInstanceOf[BoardTrait] should be (true)
        BoardCreator(8).init(BoardCreator(8).create).moveBlack('A', '7', 'A', '6').isInstanceOf[BoardTrait] should be (true)
      }
      "be moved when it's player two's turn" in {
        BoardCreator(8).init(BoardCreator(8).create).moveBlack('A', '2', 'A', '3').Matrix(0)(1).figure.isInstanceOf[None] should be (true)
      }
    }
    "white" should {
      "not be moved when it's player two's turn" in {
        BoardCreator(8).init(BoardCreator(8).create).moveWhite('A', '2', 'A', 'Z').isInstanceOf[BoardTrait] should be (true)
        BoardCreator(8).init(BoardCreator(8).create).moveWhite('A', '2', 'A', '3').isInstanceOf[BoardTrait] should be (true)
      }
      "be moved when it's player one's turn" in {
        BoardCreator(8).init(BoardCreator(8).create).moveWhite('A', '7', 'A', '6').Matrix(0)(6).figure.isInstanceOf[None] should be (true)
      }
    }
  }
  "Figures" should {
    var tmp = BoardCreator(8).init(BoardCreator(8).create)
    "be able to hit figures that have another color" in {
      tmp.move('A', '8', 'A', '2').Matrix(0)(7).figure.isInstanceOf[None] should be (true)
    }
  }

  "A BoardMock" should {
    var mock = new chess.model.boardComponent.boardMockImpl.Board(8)
    val mock2 = mock
    "have a state" in {
      mock.state should be (0)
    }
    "have a size" in {
      mock.size should be (8)
    }
    "not do anything when putting a new figure on it" in {
      mock.put('A', '1', 'P', 'B') should be (mock2)
    }
    "return true if coords are tested for validity, whether or not they are valid" in {
      mock.validCoords('1', '1', '1', '1') should be (true)
    }
    "have an empty Matrix" in {
      mock.Matrix.isInstanceOf[Array[Array[Cell]]] should be (true)
    }
    "not do anything when moving a black figure" in {
      mock.moveBlack('A', '1', 'A', '3') should be (mock2)
    }
    "not do anything when moving a white figure" in {
      mock.moveWhite('A', '1', 'A', '2') should be (mock2)
    }
    "not do anything when moving a figure" in {
      mock.move('A', '1', 'A', '2') should be (mock2)
    }
    "always return -1 when checking the positional value of a char (so every char is not a value of the board)" in {
      mock.xi('A') should be (-1)
      mock.yi('A') should be (-1)
    }
    "do nothing when taking one step back" in {
      mock.back('A', '1', 'A', 'A') should be (mock2)
    }
  }
}

//"\n" + "   A  B  C  D  E  F  G  H\n" + ((("y " + ((Console.WHITE_B + " x " +
//        Console.RESET + " x ") * (test.size / 2))) + "\n" +
//        ("y " + ((" x " + Console.WHITE_B  + " x " + Console.RESET) * (test.size / 2))) + "\n") *
//        (test.size / 2))