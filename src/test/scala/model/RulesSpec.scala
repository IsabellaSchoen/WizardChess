package model

import chess.model.{BoardCreator, Rules}
import org.scalatest.{Matchers, WordSpec}

class RulesSpec extends WordSpec with Matchers{
  "a pawn" should {
    "be allowed to move" in {
      Rules.valid(BoardCreator(8).create, 0, 1, 0, 2) should be (true)
      Rules.valid(BoardCreator(8).create, 0, 1, 0, 3) should be (true)
      Rules.valid(BoardCreator(8).create, 0, 6, 0, 4) should be (true)
      Rules.valid(BoardCreator(8).create, 0, 6, 0, 5) should be (true)
    }
  }
}
