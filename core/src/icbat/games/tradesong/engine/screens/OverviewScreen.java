package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.screens.components.MoneyCounter;
import icbat.games.tradesong.engine.screens.components.TurnCounter;

/***/
public class OverviewScreen extends AbstractBaseScreen {

    public OverviewScreen() {
        backgroundColor.setGreen(0.3f);
    }

    @Override
    protected void buildStage() {
        stage.clear();
        final Table layout = buildLayout();
        stage.addActor(layout);
    }

    private Table buildLayout() {
        final Table table = new Table(TradesongGame.skin);
        table.setFillParent(true);
        table.align(Align.top);
        table.add("Overview").colspan(2);
        table.row();
        table.add(new MoneyCounter(TradesongGame.holdings)).pad(50);
        table.add(new TurnCounter(TradesongGame.turnTaker)).pad(50);
        table.row();
        return table;
    }
}
