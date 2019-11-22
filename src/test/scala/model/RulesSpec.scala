package model

import chess.model.{Board, BoardCreator, Rules}
import org.scalatest.{Matchers, WordSpec}

class RulesSpec extends WordSpec with Matchers{
  val test: Board = BoardCreator(8).init(BoardCreator(8).create)
  "a black pawn" when {
    "not having moved yet" should {
      "be allowed to move one step south" in {
        Rules.valid(test, 0, 1, 0, 2) should be(true)
      }
      "be allowed to move two steps south" in {
        Rules.valid(test, 0, 1, 0, 3) should be (true)
        test.move('A', '2', 'A', '4')
      }
    }
    "already having moved" should {
      "be allowed to move one step south" in {
        Rules.valid(test, 0, 3, 0, 4) should be(true)
      }
      "not be allowed to move two steps south" in {
        Rules.valid(test, 0, 3, 0, 5) should be (false)
      }
    }
  }
  "a black pawn" should {
    "not be allowed to move north, east or west" in {
      Rules.valid(test, 0, 3, 0, 2) should be (false)
    }
    "not be allowed to move west" in {
      Rules.valid(test, 0, 3, 1, 3) should be (false)
    }
    "not be allowed to move east" in {
      Rules.valid(test, 1, 2, 0, 2) should be (false)
    }
  }
  "a white pawn" when {
    "not having moved yet" should {
      "be allowed to move one step north" in {
        Rules.valid(test, 0, 6, 0, 5) should be (true)
      }
      "be allowed to move two steps north" in {
        Rules.valid(test, 0, 6, 0, 4) should be (true)
        test.move('A', '7', 'A', '5')
      }
    }
    "already having moved" should {
      "be allowed to move one step north" in {
        Rules.valid(test, 0, 4, 0, 3) should be(true)
      }
      "not be allowed to move two steps north" in {
        Rules.valid(test, 0, 4, 0, 2) should be (false)
      }
    }
  }
  "a white pawn" should {
    "not be allowed to move south" in {
      Rules.valid(test, 0, 4, 0, 5) should be(false)
    }
    "not be allowed to move east" in {
      Rules.valid(test, 0, 4, 1, 4) should be(false)
    }
    "not be allowed to move west" in {
      Rules.valid(test, 1, 6, 0, 6) should be(false)
    }
  }

  val king: Board = BoardCreator(8).init(BoardCreator(8).create)
  king.put('D', '5', 'K', 'B')
  "a king" should {
    "be able to move one step south" in {
      Rules.valid(king, 3, 4, 3, 5) should be (true)
    }
    "not be able to move two or more steps south" in {
      Rules.valid(king, 3, 4, 3, 6) should be (false)
    }
    "be able to move one step north" in {
      Rules.valid(king, 3, 4, 3, 3) should be (true)
    }
    "not be able to move two or more steps north" in {
      Rules.valid(king, 3, 4, 3, 2) should be (false)
    }
    "be able to move one step east" in {
      Rules.valid(king, 3, 4, 4, 4) should be (true)
    }
    "not be able to move two or more steps east" in {
      Rules.valid(king, 3, 4, 5, 4) should be (false)
    }
    "be able to move one step west" in {
      Rules.valid(king, 3, 4, 2, 4) should be (true)
    }
    "not be able to move two or more steps west" in {
      Rules.valid(king, 3, 4, 1, 4) should be (false)
    }
  }

  val rook: Board = BoardCreator(8).init(BoardCreator(8).create)
  rook.put('D', '5', 'R', 'B')
  "a rook" should {
    "be allowed to move straight" in {
      Rules.valid(rook, 3, 4, 3, 5) should be (true)
      Rules.valid(rook, 3, 4, 3, 6) should be (true)
      Rules.valid(rook, 3, 4, 3, 3) should be (true)
      Rules.valid(rook, 3, 4, 3, 2) should be (true)
      Rules.valid(rook, 3, 4, 2, 4) should be (true)
      Rules.valid(rook, 3, 4, 1, 4) should be (true)
      Rules.valid(rook, 3, 4, 4, 4) should be (true)
      Rules.valid(rook, 3, 4, 5, 4) should be (true)
    }
    "not be allowed to move diagonal" in {
      Rules.valid(rook, 3, 4, 4, 5) should be (false)
      Rules.valid(rook, 3, 4, 4, 3) should be (false)
      Rules.valid(rook, 3, 4, 2, 5) should be (false)
      Rules.valid(rook, 3, 4, 2, 3) should be (false)
    }
  }

  "a horse" should {

  }

  val bishop: Board = BoardCreator(8).init(BoardCreator(8).create)
  king.put('D', '4', 'B', 'B')
  "a bishop" should {
    "be able to move rigth down" in{
      Rules.valid(bishop, 4, 3, 5, 4) should be (true)
      Rules.valid(bishop, 4, 3, 6, 5) should be (true)
    }
    "be able to move left down" in{
      Rules.valid(bishop, 4, 3, 3, 4) should be (true)
      Rules.valid(bishop, 4, 3, 2, 5) should be (true)
    }
    "be able to move right up" in {
      Rules.valid(bishop, 4, 3, 5, 2) should be(true)
      Rules.valid(bishop, 4, 3, 6, 1) should be(true)
    }
    "be able to move left up" in {
      Rules.valid(bishop, 4, 3, 3, 2) should be(true)
      Rules.valid(bishop, 4, 3, 2, 1) should be(true)
    }
  }

  "a queen" should {

  }
}