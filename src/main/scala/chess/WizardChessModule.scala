package chess


import chess.control.controllerComponent.ControllerTrait
import chess.control.controllerComponent.controllerBaseImpl.Controller
import chess.model.BoardCreator
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
    bind[ControllerTrait].to[Controller]
    bind[BoardTrait].annotatedWithName("normal").toInstance(BoardCreator(8).create)
    bind[BoardTrait].annotatedWithName("twice").toInstance(new BoardCreator(16).create)
    bind[BoardTrait].annotatedWithName("triple").toInstance(new BoardCreator(32).create)

  }

}