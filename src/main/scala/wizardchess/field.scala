package wizardchess

class field {

  var matrix = Array.ofDim[Int](8, 8)


  val b1 = new board
  for (y <- 0 to 7) {
    for (x <- 0 to 7) {
      val letters = y match {
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


}
