package model

import chess.model.{BoardCreator, RulesBlack}
import org.scalatest.{Matchers, WordSpec}

class RulesBlackSpec extends WordSpec with Matchers{
  "A black figure" should {
    "be allowed to move" in {
      RulesBlack.valid(BoardCreator(8).init(BoardCreator(8).create), 0, 1, 0, 2) should be (true)
    }
  }
  "A white figure" should {
    "not be allowed to move" in {
      RulesBlack.valid(BoardCreator(8).init(BoardCreator(8).create), 0, 6, 0, 5) should be (false)
    }
  }
}
