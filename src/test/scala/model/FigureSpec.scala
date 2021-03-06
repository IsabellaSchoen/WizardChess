package model

import org.scalatest.{Matchers, WordSpec}
import chess.model._

class FigureSpec extends WordSpec with Matchers{
  "A Figure" should {
    "have a name" in {
      Figure("king").toString should be ("king")
      Figure("pawn").toString should be ("pawn")
      Figure("queen").toString should be ("queen")
      Figure("bishop").toString should be ("bishop")
      Figure("rook").toString should be ("rook")
      Figure("horse").toString should be ("horse")
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
  "A black pawn" should {
    "be able to hit other figures, when they stand diagonal south from them" in {
      new Pawn('B').hit(0, 0, 1, 1) should be (true)
      new Pawn('B').hit(0, 0, 1, 2) should be (false)
    }
    "be able to hit other figures, when they stand diagonal north from them" in {
      new Pawn('W').hit(7, 7, 6, 6) should be (true)
      new Pawn('W').hit(7, 7, 6, 5) should be (false)
    }
  }
}
