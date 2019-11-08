package model

import org.scalatest.{Matchers, WordSpec}
import chess.model._

class CellSpec extends WordSpec with Matchers{
  var test : Cell = Cell('B', 7, Some(Figure("TEST")))
  "A Cell" should {
    "have a x value" in {
      Cell('A', 1).x should be ('A')
      Cell('A', 1).getX should be (0)
    }
    "have a y value" in {
      Cell('A', 1).y should be (1)
      Cell('A', 1).getY should be (0)
    }
  }
  "A Cell" when {
    "empty" should {
      "not have a figure" in {
        Cell('D', 7).isEmpty should be (true)
      }
    }
    "not empty" should {
      test = test.set("pawn")
      "have a figure" in {
        test.isEmpty should be (false)
        }
      "have a String representation for its figure" in {
        test.toString should be ("pawn B7")
      }
    }
    "being set" should{
      "get a new Figure" in {
        test.set("newCell").figure.get.toString.equals("newCell") should be (true)
      }
    }
  }
}
