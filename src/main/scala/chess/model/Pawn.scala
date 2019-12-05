package chess.model

class Pawn(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = PawnState.handle(x1, y1, x2, y2)

  object PawnState {
    var state: () => Boolean = twoStep
    var xs, ys, xe, ye = -1
    def handle(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
      xs = x1; ys = y1; xe = x2; ye = y2
      val backup = state
      state = oneStep
      backup()
    }
    def twoStep: () => Boolean = {
      println("twostep")
      color match {
        case 'B' => () => (oneStep() || (xs == xe && ye == ys + 2))
        case 'W' => () => (oneStep() || (xs == xe && ye == ys - 2))
      }
    }

    def oneStep: () => Boolean = {
      println("onestep")
      color match {
        case 'B' => () => (xs == xe && ye == ys + 1)
        case 'W' => () => (xs == xe && ye == ys - 1)
      }
    }
  }

  override def caption: Char = 'P'

  override def toString: String = "pawn"
}
