import chess.model.BoardCreator

var board = BoardCreator(8).init(BoardCreator(8).create)

print(board.toString)

board.move('A', '2', 'A', '4')

board.back('A', '2', 'A', '4')

board.move('A', '2', 'A', '4')