package chess.model.FileIOComponents

import chess.model.boardComponent.BoardTrait


trait FileIOInterface {

  def load: BoardTrait

  def save(board: BoardTrait): Unit

}