package model

import chess.model.boardComponent.BoardTrait
import chess.model.{BoardCreator, None, Pawn, RulesAll}
import org.scalatest.{Matchers, WordSpec}

class RulesAllSpec extends WordSpec with Matchers {
  val test: BoardTrait = BoardCreator(8).init(BoardCreator(8).create)
  "a black pawn" when {
    "not having moved yet" should {
      "be allowed to move one step south" in {
        RulesAll.valid(BoardCreator(8).init(BoardCreator(8).create), 0, 1, 0, 2) should be(true)
      }
      "be allowed to move two steps south" in {
        test.move('A', '2', 'A', '4').Matrix(0)(1).figure.isInstanceOf[None] should be(true)
      }
    }
    "already having moved" should {
      "be allowed to move one step south" in {
        RulesAll.valid(test, 0, 3, 0, 4) should be(true)
      }
      "not be allowed to move two steps south" in {
        RulesAll.valid(test, 0, 3, 0, 5) should be(false)
      }
    }
    "should be able to hit a figure" in {
      var tmp = BoardCreator(8).create.put('A', '1', 'P', 'B').put('B', '2', 'P', 'W')
      RulesAll.hit(tmp, 0, 0, 1, 1) should be (true)
      RulesAll.hit(tmp, 1, 1, 0, 0) should be (true)
    }
  }

  "a black pawn" should {
    "not be allowed to move north" in {
      RulesAll.valid(test, 0, 3, 0, 2) should be(false)
    }
    "not be allowed to move west" in {
      RulesAll.valid(test, 0, 3, 1, 3) should be(false)
    }
    "not be allowed to move east" in {
      RulesAll.valid(test, 1, 2, 0, 2) should be(false)
    }
  }

  var test2: BoardTrait = BoardCreator(8).init(BoardCreator(8).create)

  "a white pawn" when {
    "not having moved yet" should {
      "be allowed to move one step north" in {
        RulesAll.valid(BoardCreator(8).init(BoardCreator(8).create), 0, 6, 0, 5) should be(true)
      }
      "be allowed to move two steps north" in {
        test2.move('A', '7', 'A', '5').Matrix(0)(4).figure.isInstanceOf[None] should be(false)
      }
    }
    "already having moved" should {
      "be allowed to move one step north" in {
        RulesAll.valid(test2, 0, 4, 0, 3) should be(true)
      }
      "not be allowed to move two steps north" in {
        RulesAll.valid(test2, 0, 4, 0, 2) should be(false)
      }
    }
  }
  "a white pawn" should {
    "not be allowed to move south" in {
      RulesAll.valid(test2, 0, 4, 0, 5) should be(false)
    }
    "not be allowed to move east" in {
      RulesAll.valid(test2, 0, 4, 1, 4) should be(false)
    }
    "not be allowed to move west" in {
      RulesAll.valid(test2, 1, 6, 0, 6) should be(false)
    }
  }

  val king: BoardTrait = BoardCreator(8).create
  king.put('D', '5', 'K', 'B')
  "a king" should {
    "be able to move one step south" in {
      RulesAll.valid(king, 3, 4, 3, 5) should be(true)
    }
    "not be able to move two or more steps south" in {
      RulesAll.valid(king, 3, 4, 3, 6) should be(false)
    }
    "be able to move one step north" in {
      RulesAll.valid(king, 3, 4, 3, 3) should be(true)
    }
    "not be able to move two or more steps north" in {
      RulesAll.valid(king, 3, 4, 3, 2) should be(false)
    }
    "be able to move one step east" in {
      RulesAll.valid(king, 3, 4, 4, 4) should be(true)
    }
    "not be able to move two or more steps east" in {
      RulesAll.valid(king, 3, 4, 5, 4) should be(false)
    }
    "be able to move one step west" in {
      RulesAll.valid(king, 3, 4, 2, 4) should be(true)
    }
    "not be able to move two or more steps west" in {
      RulesAll.valid(king, 3, 4, 1, 4) should be(false)
    }
  }

  val rook: BoardTrait = BoardCreator(8).create
  rook.put('D', '5', 'R', 'B')
  "a rook" should {
    "be allowed to move south" in {
      RulesAll.valid(rook, 3, 4, 3, 5) should be(true)
      RulesAll.valid(rook, 3, 4, 3, 6) should be(true)
    }
    "be allowed to move north" in {
      RulesAll.valid(rook, 3, 4, 3, 3) should be(true)
      RulesAll.valid(rook, 3, 4, 3, 2) should be(true)
    }
    "be allowed to move east" in {
      RulesAll.valid(rook, 3, 4, 4, 4) should be(true)
      RulesAll.valid(rook, 3, 4, 5, 4) should be(true)
    }
    "be allowed to move west" in {
      RulesAll.valid(rook, 3, 4, 2, 4) should be(true)
      RulesAll.valid(rook, 3, 4, 1, 4) should be(true)
    }
    "not be allowed to move diagonal" in {
      RulesAll.valid(rook, 3, 4, 4, 5) should be(false)
      RulesAll.valid(rook, 3, 4, 4, 3) should be(false)
      RulesAll.valid(rook, 3, 4, 2, 5) should be(false)
      RulesAll.valid(rook, 3, 4, 2, 3) should be(false)
    }
  }


  val horse: BoardTrait = BoardCreator(8).create
  horse.put('D', '5', 'H', 'B')
  "a horse" should {
    "be able to move right up in three steps" in {
      RulesAll.valid(horse, 3, 4, 4, 2) should be(true)
      RulesAll.valid(horse, 3, 4, 2, 2) should be(true)
      RulesAll.valid(horse, 3, 4, 5, 3) should be(true)
      RulesAll.valid(horse, 3, 4, 1, 3) should be(true)
      RulesAll.valid(horse, 3, 4, 4, 6) should be(true)
      RulesAll.valid(horse, 3, 4, 5, 5) should be(true)
      RulesAll.valid(horse, 3, 4, 4, 6) should be(true)
      RulesAll.valid(horse, 3, 4, 2, 6) should be(true)
      RulesAll.valid(horse, 3, 4, 1, 5) should be(true)
    }
    "not be allowed to move diagonal and straight" in {
      RulesAll.valid(horse, 3, 4, 4, 5) should be(false)
      RulesAll.valid(horse, 3, 4, 4, 3) should be(false)
      RulesAll.valid(horse, 3, 4, 2, 5) should be(false)
      RulesAll.valid(horse, 3, 4, 2, 3) should be(false)
      RulesAll.valid(horse, 3, 4, 3, 5) should be(false)
      RulesAll.valid(horse, 3, 4, 3, 3) should be(false)
      RulesAll.valid(horse, 3, 4, 2, 4) should be(false)
      RulesAll.valid(horse, 3, 4, 5, 4) should be(false)
    }


    val bishop: BoardTrait = BoardCreator(8).create
    bishop.put('D', '4', 'B', 'B')
    println(bishop)
    "a bishop" should {
      "be able to move right down" in {
        RulesAll.valid(bishop, 3, 3, 4, 4) should be(true)
        RulesAll.valid(bishop, 3, 3, 5, 5) should be(true)
      }
      "be able to move left down" in {
        RulesAll.valid(bishop, 3, 3, 2, 4) should be(true)
        RulesAll.valid(bishop, 3, 3, 1, 5) should be(true)
      }
      "be able to move right up" in {
        RulesAll.valid(bishop, 3, 3, 4, 2) should be(true)
        RulesAll.valid(bishop, 3, 3, 5, 1) should be(true)
      }
      "be able to move left up" in {
        RulesAll.valid(bishop, 3, 3, 2, 2) should be(true)
        RulesAll.valid(bishop, 3, 3, 1, 1) should be(true)
      }
      "not be allowed to run straight" in {
        RulesAll.valid(bishop, 3, 3, 3, 4) should be(false)
        RulesAll.valid(bishop, 3, 3, 3, 2) should be(false)
        RulesAll.valid(bishop, 3, 3, 2, 3) should be(false)
        RulesAll.valid(bishop, 3, 3, 4, 3) should be(false)
      }
    }


    val queen: BoardTrait = BoardCreator(8).create
    queen.put('D', '4', 'Q', 'B')
    "a queen" should {
      "be able to move right down" in {
        RulesAll.valid(queen, 3, 3, 4, 4) should be(true)
        RulesAll.valid(queen, 3, 3, 5, 5) should be(true)
      }
      "be able to move left down" in {
        RulesAll.valid(queen, 3, 3, 2, 4) should be(true)
        RulesAll.valid(queen, 3, 3, 1, 5) should be(true)
      }
      "be able to move right up" in {
        RulesAll.valid(queen, 3, 3, 4, 2) should be(true)
        RulesAll.valid(queen, 3, 3, 5, 1) should be(true)
      }
      "be able to move left up" in {
        RulesAll.valid(queen, 3, 3, 2, 2) should be(true)
        RulesAll.valid(queen, 3, 3, 1, 1) should be(true)
      }
      "be allowed to move straight" in {
        RulesAll.valid(queen, 3, 3, 3, 5) should be(true)
        RulesAll.valid(queen, 3, 3, 3, 4) should be(true)
        RulesAll.valid(queen, 3, 3, 5, 3) should be(true)
        RulesAll.valid(queen, 3, 3, 4, 3) should be(true)
        RulesAll.valid(queen, 3, 3, 2, 3) should be(true)
        RulesAll.valid(queen, 3, 3, 1, 3) should be(true)
        RulesAll.valid(queen, 3, 3, 3, 2) should be(true)
        RulesAll.valid(queen, 3, 3, 3, 1) should be(true)
      }
    }
  }
}