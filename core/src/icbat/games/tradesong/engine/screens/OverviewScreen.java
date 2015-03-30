package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.screens.components.GoToScreenButton;
import icbat.games.tradesong.engine.screens.components.MoneyCounter;
import icbat.games.tradesong.engine.screens.components.TurnCounter;
import icbat.games.tradesong.engine.screens.components.VerticalWorkshopDisplay;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.List;

/***/
public class OverviewScreen extends AbstractBaseScreen {

    public OverviewScreen() {
        backgroundColor.setGreen(0.3f);
    }

    @Override
    protected Table buildLayout() {
        final Table layout = new Table(TradesongGame.skin);
        layout.setFillParent(true);
        layout.align(Align.top);
        layout.add("Overview").colspan(2);
        layout.row();
        layout.add(new MoneyCounter(TradesongGame.holdings)).pad(50);
        layout.add(new TurnCounter(TradesongGame.turnTaker)).pad(50);
        layout.row();
        layout.add("Active Workshops").row();
        layout.add(new VerticalWorkshopDisplay(TradesongGame.holdings.getWorkshops()));
        layout.add(makeVerticalWorkshopButtons(TradesongGame.holdings.getWorkshops()));
        return layout;
    }

    private Table makeVerticalWorkshopButtons(List<Workshop> workshops) {
        final Table table = new Table(TradesongGame.skin);
        for (Workshop workshop : workshops) {
            table.add(new GoToScreenButton(workshop)).row();
        }
        return table;
    }
}
