// Prototypes for classes

class figure(id: Int) {
  val name = id match {
    case 1 => "pawn"
    case _ => null
  }
}

val testpawn = new figure(1)
val testnone = new figure(0)

println(testpawn.name)
println(testnone.name)

class cell(x: Int, y: Char) {
  var figure = null.asInstanceOf[figure]
  val posX = x
  val posY = y
}

val cell_1 = new cell(1,'A')

cell_1.figure = testpawn

println(cell_1.figure.name)

println(cell_1.posX.toString + cell_1.posY)

var matrix = Array.ofDim[Int](2,2)
matrix(0)(0) = 1