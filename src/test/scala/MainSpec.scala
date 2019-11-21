import chess.WizardChess
import org.scalatest.{Matchers, WordSpec}

class MainSpec extends WordSpec with Matchers {
  "main" should {
    "run the main game" in{
      WizardChess.main(Array("exit")) should be ()
    }
  }
}
