import chess.model.Figure

var x: Vector[Figure] = Vector(Figure("bishop"))

x = x.appended(Figure("queen")) :+ Figure("king")

x.toString()

x = x.filterNot(o => o == Figure("queen"))

x.toString()