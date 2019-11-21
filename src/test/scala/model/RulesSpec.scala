package model

import chess.model.{Board, BoardCreator, Rules}
import org.scalatest.{Matchers, WordSpec}

class RulesSpec extends WordSpec with Matchers{
  "a pawn" should {
    "be allowed to move" in {
      val test: Board = BoardCreator(8).create
      Rules.valid(test, 0, 1, 0, 2) should be (true)
      test.move('A', '2', 'A', '3')
      Rules.valid(test, 1, 1, 1, 3) should be (true)
      test.move('B', '2', 'B', '4')
      Rules.valid(test, 0, 6, 0, 4) should be (true)
      test.move('A', '7', 'A', '5')
      Rules.valid(test, 1, 6, 1, 5) should be (true)
      test.move('B', '7', 'B', '6')
      Rules.valid(test, 0, 2, 0, 3) should be (true)
      Rules.valid(test, 0, 2, 0, 4) should be (false)
      Rules.valid(test, 0, 5, 0, 4) should be (true)
      Rules.valid(test, 0, 5, 0, 3) should be (false)
    }
  }
}
