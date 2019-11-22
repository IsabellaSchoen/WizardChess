package model

import chess.model.{Board, BoardCreator, Rules}
import org.scalatest.{Matchers, WordSpec}

class RulesSpec extends WordSpec with Matchers{
  val test: Board = BoardCreator(8).init(BoardCreator(8).create)
  "a black pawn" when {
    "not having moved yet" should {
      "be allowed to move one step south" in {
        Rules.valid(test, 0, 1, 0, 2) should be(true)
        // TODO negativ-beispiel
      }
      "be allowed to move two steps south" in {
        Rules.valid(test, 0, 1, 0, 3) should be (true)
        test.move('A', '2', 'A', '4')
        // TODO negativ-beispiel
      }
    }
    "already having moved" should {
      "be allowed to move one step south" in {
        Rules.valid(test, 0, 3, 0, 4) should be(true)
        // TODO negativ-beispiel
      }
      "not be allowed to move two steps south" in {
        Rules.valid(test, 0, 3, 0, 5) should be (false)
      }
    }
  }
  "a white pawn" when {
    "not having moved yet" should {
      "be allowed to move one step north" in {
        Rules.valid(test, 0, 6, 0, 5) should be (true)
        // TODO negativ-beispiel
      }
      "be allowed to move two steps north" in {
        Rules.valid(test, 0, 6, 0, 4) should be (true)
        // TODO negativ-beispiel
        test.move('A', '7', 'A', '5')
      }
    }
    "already having moved" should {
      "be allowed to move one step north" in {
        Rules.valid(test, 0, 4, 0, 3) should be(true)
        // TODO negativ-beispiel
      }
      "not be allowed to move two steps north" in {
        Rules.valid(test, 0, 4, 0, 2) should be (false)
      }
    }
  }

  val king: Board = BoardCreator(8).init(BoardCreator(8).create)
  "a black kind" should {
    "be able to move south" in {

    }
  }
}