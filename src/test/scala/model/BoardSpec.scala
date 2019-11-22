package model

import chess.model._
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers {
  "A board" should {
    "be a matrix" in {
      Board(8).Matrix.isInstanceOf[Array[Array[Cell]]] should be (true)
    }
    "have a string representation" in {
      Board(8).Matrix(0)(0) should be (null)
      val test: Board = BoardCreator(8).create
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
      val t1: Board = BoardCreator(8).init(BoardCreator(8).create)
      val t2: Board = t1
      t1.move('Z', 'Z', 'Z', 'Z') should be (t2)
      t1.move('A', '2', 'B', '4') should be (t2)
      t1.move('A', '1', 'A', '2') should be (t2)
      t1.move('A', '2', 'A', '4').Matrix(0)(1).figure should be (None)
      t1.Matrix(0)(3).figure.get.caption should be ('P')
    }
  }
}

//"\n" + "   A  B  C  D  E  F  G  H\n" + ((("y " + ((Console.WHITE_B + " x " +
//        Console.RESET + " x ") * (test.size / 2))) + "\n" +
//        ("y " + ((" x " + Console.WHITE_B  + " x " + Console.RESET) * (test.size / 2))) + "\n") *
//        (test.size / 2))