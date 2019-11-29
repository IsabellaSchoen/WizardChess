package model

import chess.model.{BoardCreator, RulesWhite}
import org.scalatest.{Matchers, WordSpec}

class RulesWhiteSpec extends WordSpec with Matchers{
  "A white figure" should {
    "be allowed to move" in {
      RulesWhite.valid(BoardCreator(8).init(BoardCreator(8).create), 0, 6, 0, 5) should be (true)
    }
  }
  "A black figure" should {
    "not be allowed to move" in {
      RulesWhite.valid(BoardCreator(8).init(BoardCreator(8).create), 0, 1, 0, 2) should be (false)
    }
  }
}
