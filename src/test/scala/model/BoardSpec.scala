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
    }
  }
}
