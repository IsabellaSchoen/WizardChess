package model

import chess.model._
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers {
  "A board" should {
    "be a matrix" in {
      Board(8).Matrix.isInstanceOf[Array[Array[Cell]]] should be (true)
    }
    "have a string representation" in {
      Board(8).toString should be ("empty Board")
      Board(8).Matrix(0)(0) should be (null)
      val test: Board = BoardCreator(8).create
      test.toString should be (test.toString)
    }
    "have Cells" which {
      "have an x value" in {
        Board(8).xi('A') should be (0)
      }
      "have a y value" in {
        Board(8).yi('1') should be (0)
      }
    }
  }
}

//"\n" + "   A  B  C  D  E  F  G  H\n" + ((("y " + ((Console.WHITE_B + " x " +
//        Console.RESET + " x ") * (test.size / 2))) + "\n" +
//        ("y " + ((" x " + Console.WHITE_B  + " x " + Console.RESET) * (test.size / 2))) + "\n") *
//        (test.size / 2))