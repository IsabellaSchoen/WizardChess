package chess.model.FileIOComponents

import chess.model.BoardCreator


trait FileIOInterface {
  def load: BoardCreator
  def save(board: BoardCreator): Unit
}