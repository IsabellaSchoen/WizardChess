package control

import chess.control.controllerComponent.controllerBaseImpl.Controller
import chess.model.{BoardCreator, None}
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
  var test = new Controller(BoardCreator(8).init(BoardCreator(8).create))
  "A controller" when {
    "in state 0" should {
      "have state 0" in {
        test.setState(0)
      }
      "be able to move all figures" in {
        test.move('A', '2', 'A', '3')
        test.board.Matrix(0)(1).figure.isInstanceOf[None] should be(true)
        test.move('A', '7', 'A', '6')
        test.board.Matrix(0)(6).figure.isInstanceOf[None] should be(true)
      }
    }

    "in state 1" should {
      "have state 1" in {
        test.setState(1)
      }
      "just be able to move white figures" in {
        test.move('B', '2', 'B', '3')
        test.board.Matrix(1)(1).figure.isInstanceOf[None] should be(false)
        test.move('B', '7', 'B', '6')
        test.board.Matrix(1)(6).figure.isInstanceOf[None] should be(true)
      }
    }

    "in state 2" should {
      "have state 2" in {
        test.setState(2)
      }
      "just be able to move black figures" in {
        test.move('C', '7', 'C', '6')
        test.board.Matrix(2)(6).figure.isInstanceOf[None] should be(false)
        test.move('C', '2', 'C', '3')
        test.board.Matrix(2)(1).figure.isInstanceOf[None] should be(true)
      }
    }
  }

  "A controller" should {
    "be able to execute a command" in {
      var mock = new chess.control.controllerComponent.controllerMockImpl.Controller
      mock.move('a', 'a', 'a', 'a') should be ()
      mock.put('a', 'a', 'a', 'a') should be ()
      mock.empty() should be ()
      mock.boardToString.equals("") should be (true)
      mock.setState(1) should be ()
      mock.moveAll('a', 'a', 'a', 'a') should be ()
      mock.moveOne('a', 'a', 'a', 'a') should be ()
      mock.moveTwo('a', 'a', 'a', 'a') should be ()
      mock.create should be ()
      mock.getFig(1, 1).equals("") should be (true)
      mock.save should be ()
      mock.load() should be ()
      mock.getState() should be (0)
      mock.undo() should be ()
      mock.redo should be ()
      mock.back('a', 'a', 'a', 'a') should be ()
    }
    var test = new chess.control.controllerComponent.controllerBaseImpl.Controller(BoardCreator(8).init(BoardCreator(8).create))
    "create a new board" in {
      var test = new chess.control.controllerComponent.controllerBaseImpl.Controller(BoardCreator(8).init(BoardCreator(8).create))
      test.createNewBoard()
      var test2 = new chess.control.controllerComponent.controllerBaseImpl.Controller(BoardCreator(16).init(BoardCreator(16).create))
      test2.createNewBoard()
      var test3 = new chess.control.controllerComponent.controllerBaseImpl.Controller(BoardCreator(32).init(BoardCreator(32).create))
      test3.createNewBoard()
    }
    "undo and redo commands" in {
      test.undo should be ()
      test.redo should be ()
      test.move('A', '8', 'A', '6')
      test.undo
      test.board.Matrix(0)(7).figure.isInstanceOf[None] should be (false)
      test.redo
      test.board.Matrix(0)(7).figure.isInstanceOf[None] should be (true)
    }
    "take a step back" in {
      test.back('A', '2', 'A', '3') should be ()
      test.back('A', '2', 'A', '3') should be ()
    }
    "see which figure stands on a cell" in {
      test.getFig(7, 0) should be ("rook_black")
      test.getFig(7, 7) should be ("rook_white")
      test.getFig(0, 1) should be ("pawn_black")
      test.getFig(1, 0) should be ("horse_black")
      test.getFig(2, 0) should be ("bishop_black")
      test.getFig(3, 0) should be ("queen_black")
      test.getFig(4, 0) should be ("king_black")
      test.getFig(3, 3) should be ("_black")
    }
    "save and load a board with FileIO" in {
      test.save should be ()
      test.load should be ()
    }
  }
}
