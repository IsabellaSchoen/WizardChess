package model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers{
  "A Cell" should {
    "have a x value" in {
      new Cell('A', 1).x should be ('A')
    }
    "have a y value" in {
      new Cell('A', 1).y should be (1)
    }
  }
  "A Cell" when {
    "empty" should {
      "not have a figure" in {
        new Cell('D', 7).isEmpty should be (true)
      }
    }
    "not empty" should {
      var test : Cell = new Cell('B', 7, Some(Figure("TEST")))
      test = test.set("pawn")
      "have a figure" in {
        test.isEmpty should be (false)
        }
      "have a String representation for its figure" in {
        test.toString should be ("pawn B7")
      }
    }
  }
}
