package model

import org.scalatest.{Matchers, WordSpec}
import chess.model._

class FigureSpec extends WordSpec with Matchers{
  "A Figure" should {
    "have a name" in {
      Figure("king").toString should be ("king")
    }
    "have a color" in {
      Figure("king").col should be ('B')
    }
    "have an identification Char" in {
      Figure().caption should be ('P')
      Figure("bishop").caption should be ('B')
      Figure("rook").caption should be ('R')
      Figure("king").caption should be ('K')
      Figure("queen").caption should be ('Q')
      Figure("horse").caption should be ('H')
      Figure("none").caption should be (' ')
    }
    "have a movement rule" in {
      Figure("none").figRule(0, 0, 0, 0) should be (false)
      Figure.translate('n') should be ("none")
    }
  }
}
