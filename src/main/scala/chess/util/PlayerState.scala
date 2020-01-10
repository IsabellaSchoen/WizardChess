package chess.util

trait State{
  def changeState():State
}

object PlayerState{
  var state : State = playerWhite

    def handle():State = state.changeState()

  //noinspection DuplicatedCode
  object playerWhite extends State{
    override def changeState(): State = {
      state = playerBlack
      state
    }
  }

  //noinspection DuplicatedCode
  object playerBlack extends State{
    override def changeState(): State = {
      state = playerWhite
      state
    }
  }
}