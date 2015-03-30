package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.screens.components.GoToScreenButton;
import icbat.games.tradesong.engine.screens.components.VerticalWorkshopDisplay;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.List;

/***/
public class OverviewScreen extends AbstractBaseScreen {

    public OverviewScreen() {
        backgroundColor.setGreen(0.3f);
    }

    @Override
    protected String getScreenName() {
        return "Overview";
    }

    @Override
    protected Table buildCentralLayout() {
        final Table layout = new Table(TradesongGame.skin);
        layout.add("Active Workshops").row();
        layout.add(new VerticalWorkshopDisplay(TradesongGame.holdings.getWorkshops())).pad(15);
        layout.add(makeVerticalWorkshopButtons(TradesongGame.holdings.getWorkshops())).pad(15);
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
