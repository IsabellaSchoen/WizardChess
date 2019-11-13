package model

import org.scalatest.{Matchers, WordSpec}
import chess.model._

class FigureSpec extends WordSpec with Matchers{
  "A Figure" should {
    "have a name" in {
      Figure("king").toString should be ("king")
    }
    "have a color" in {
      Figure("king").color should be ('B')
    }
  }
}
