package chess

import chess.control.controllerComponent.controllerBaseImpl.Controller
import chess.model.boardComponent.BoardTrait
import chess.model.boardComponent.boardBaseImpl.Board
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule


class WizardChessModule extends AbstractModule with ScalaModule {


  val defaultSize:Int = 8


  override def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[BoardTrait].to[Board]
    bind[Controller].to[Controller]
    bind[BoardTrait].annotatedWithName("tiny").toInstance(new Board(8))
    bind[BoardTrait].annotatedWithName("small").toInstance(new Board(16))
    bind[BoardTrait].annotatedWithName("normal").toInstance(new Board(32))

  }

}