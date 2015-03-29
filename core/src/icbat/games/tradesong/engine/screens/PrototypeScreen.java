package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.PrototypeLayoutTable;

public class PrototypeScreen extends AbstractBaseScreen {

    public PrototypeScreen() {
        Gdx.input.setInputProcessor(stage);
        backgroundColor.setRed(0.2f);
        backgroundColor.setGreen(0.2f);
        backgroundColor.setBlue(0.2f);
        backgroundColor.setAlpha(1);
    }

    @Override
    protected void buildStage() {
        stage.clear();
        final Table layout = new PrototypeLayoutTable(TradesongGame.turnTaker, TradesongGame.potentialWorkshops, TradesongGame.holdings, TradesongGame.contracts);
        stage.addActor(layout);
    }

}
