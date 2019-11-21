import chess.WizardChess
import org.scalatest.{Matchers, WordSpec}

class MainSpec extends WordSpec with Matchers {
  "main" should {
    "be able to exit" in{
      WizardChess.main(Array("random stuff", "exit")) should be ()
    }
  }
}
