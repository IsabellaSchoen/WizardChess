package model

import org.scalatest.{Matchers, WordSpec}

class FigureSpec extends WordSpec with Matchers{
  "A Figure" should {
    "have a name" in {
      new Figure("king").toString should be ("king")
    }
  }
}
