package model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers{
  "A Cell" should {
    "have a x value" in {
      new Cell('A', 1).getX should be ('A')
    }
    "have a y value" in {
      new Cell('A', 1).getY should be (1)
    }
  }
  "A Cell" when {
    "empty" should {
      "not have a figure" in {
        new Cell('D', 7).isEmpty should be (true)
      }
    }
    "not empty" should {
      "have a figure" in {
        var test : Cell = new Cell('B', 7)
        test = test.set(new Figure("pawn"))
        test.isEmpty should be (false)
      }
    }
  }
}
