package model

import org.scalatest.{Matchers, WordSpec}
import chess.model._

class CellSpec extends WordSpec with Matchers{
  var test : Cell = Cell(1, 7, Some(Figure("TEST")))
  "A Cell" should {
    "have a x value" in {
      Cell(0, 0).x should be (0)
    }
    "have a y value" in {
      Cell(0, 0).y should be (0)
    }
  }
  "A Cell" when {
    "empty" should {
      "not have a figure" in {
        Cell(4, 6).isEmpty should be (true)
      }
    }
    "not empty" should {
      test = test.set("pawn")
      "have a figure" in {
        test.isEmpty should be (false)
        }
      "have a String representation for its figure" in {
        test.toString should be ("pawn 17")
      }
    }
    "being set" should{
      "get a new Figure" in {
        test.set("newCell").figure.get.toString.equals("newCell") should be (true)
        test.set("pawn").figure.get.color.equals('B') should be (true)
        test.set("pawn", 'W').figure.get.color.equals('W') should be (true)
      }
    }
  }
}
