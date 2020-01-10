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
        test.board.Matrix(0)(1).figure.isInstanceOf[None] should be (true)
        test.move('A', '7', 'A', '6')
        test.board.Matrix(0)(6).figure.isInstanceOf[None] should be (true)
      }
    }
  }

  "in state 1" should {
    "have state 1" in {
      test.setState(1)
    }
    "just be able to move white figures" in {
      test.move('B', '2', 'B', '3')
      test.board.Matrix(1)(1).figure.isInstanceOf[None] should be (false)
      test.move('B', '7', 'B', '6')
      test.board.Matrix(1)(6).figure.isInstanceOf[None] should be (true)
    }
  }

  "in state 2" should {
    "have state 2" in {
      test.setState(2)
    }
    "just be able to move black figures" in {
      test.move('C', '7', 'C', '6')
      test.board.Matrix(2)(6).figure.isInstanceOf[None] should be (false)
      test.move('C', '2', 'C', '3')
      test.board.Matrix(2)(1).figure.isInstanceOf[None] should be (true)
    }
  }
}
