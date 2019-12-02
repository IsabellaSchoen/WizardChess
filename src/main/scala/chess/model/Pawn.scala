package chess.model

class Pawn(color: Char) extends Figure(color: Char) {
  override def figRule(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = true

  object PawnState {
    var state = twoStep
    def handle(e: Event) = {
      e match {
        case two : twoStepsPawn => state = twoStep
        case one : oneStepPawn => state = oneStep
      }
      state
    }
    def twoStep = println("two steps")

    def oneStep = println("one steps")
  }
}

trait Event


case class twoStepsPawn() extends Event

case class oneStepPawn() extends Event