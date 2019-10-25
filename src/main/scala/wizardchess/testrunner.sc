// Prototypes for classes


//Erstellung der Figuren
class figure(id: Int) {
  val name = id match {
    case 0 => "rook"
    case 1 => "knight"
    case 2 => "bishop"
    case 3 => "king"
    case 4 => "queen"
    case 5 => "pawn"
  }
}



/*val testPawn = new figure(1)
val nullTest = new figure(0)
println(testPawn.name)
println(nullTest.name)


val cell_1 = new cell('A', 1)
cell_1.figure = testPawn
println(cell_1.figure.name)
println(cell_1.toString())*/



//Erstellung der x- & y-Koordinaten
class cell(x: Char, y: Int) {
  var figure = null.asInstanceOf[figure]
  val posX = x
  val posY = y

  override def toString: String = {
    posX.toString + posY.toString
  }
}



//Erstellung des Spielfeldes
class board {
  val matrix = Array.ofDim[cell](8, 8)
}
val b1 = new board
for (y <- 0 to 7) {
  val numbers = y match {
    case 0 => '1'
    case 1 => '2'
    case 2 => '3'
    case 3 => '4'
    case 4 => '5'
    case 5 => '6'
    case 6 => '7'
    case 7 => '8'
  }
  for (x <- 0 to 7) {
    val letters = x match {
      case 0 => 'A'
      case 1 => 'B'
      case 2 => 'C'
      case 3 => 'D'
      case 4 => 'E'
      case 5 => 'F'
      case 6 => 'G'
      case 7 => 'H'
    }
    b1.matrix(x)(y) = new cell(letters, y + 1)
  }
}
//ausgeben
b1.matrix.foreach(a => a.foreach(c => println(c.toString())))


//Probe nach Korrektur
println(b1.matrix(4)(6)) // E7



//Figuren

//rook - Turm
b1.matrix(0)(0).figure = new figure(0)
b1.matrix(0)(7).figure = new figure(0)

b1.matrix(7)(0).figure = new figure(0)
b1.matrix(7)(7).figure = new figure(0)



//knight - Pferd
b1.matrix(0)(1).figure = new figure(1)
b1.matrix(0)(6).figure = new figure(1)

b1.matrix(7)(1).figure = new figure(1)
b1.matrix(7)(6).figure = new figure(1)



//bishops - Läufer
b1.matrix(0)(2).figure = new figure(2)
b1.matrix(0)(5).figure = new figure(2)

b1.matrix(7)(2).figure = new figure(2)
b1.matrix(7)(5).figure = new figure(2)



// queens
b1.matrix(0)(4).figure = new figure(4)

b1.matrix(7)(4).figure = new figure(4)



// kings
b1.matrix(0)(3).figure = new figure(3)

b1.matrix(7)(3).figure = new figure(3)



// pawns - Bauern
b1.matrix(1)(0).figure = new figure(5)
b1.matrix(1)(1).figure = new figure(5)
b1.matrix(1)(2).figure = new figure(5)
b1.matrix(1)(3).figure = new figure(5)
b1.matrix(1)(4).figure = new figure(5)
b1.matrix(1)(5).figure = new figure(5)
b1.matrix(1)(6).figure = new figure(5)
b1.matrix(1)(7).figure = new figure(5)

b1.matrix(6)(0).figure = new figure(5)
b1.matrix(6)(1).figure = new figure(5)
b1.matrix(6)(2).figure = new figure(5)
b1.matrix(6)(3).figure = new figure(5)
b1.matrix(6)(4).figure = new figure(5)
b1.matrix(6)(5).figure = new figure(5)
b1.matrix(6)(6).figure = new figure(5)
b1.matrix(6)(7).figure = new figure(5)



/*b1.matrix.foreach(a => a.foreach(c => { if (c.posY.equals(1) || c.posY.equals(7)) {
  c.figure = new figure(5)}}))*/


//Figuren und deren Position ausgeben:
b1.matrix.foreach(a => a.foreach(c => if (c.figure != null) {
  println(c.figure.name + " " + c)}))





class ExampleSpec extends WordSpec with Matchers {

  "A king" when {
    "set to its place " should {
      b1.matrix(0)(3).figure = new figure(3)
      " have the place A4 " in {
        b1.matrix(0)(3)
      }
      " not be set" in {
        b1.matrix.isSet should be(false)
      }
    }
  }

}