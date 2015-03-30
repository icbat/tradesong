package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workshops.Workshop;

/***/
public class WorkshopScreen extends AbstractBaseScreen {

    private final Workshop workshop;

    public WorkshopScreen(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    protected Table buildCentralLayout() {
        final Table layout = new Table(TradesongGame.skin);
        layout.add(new Label(" Workers:" + workshop.getWorkers().size(), TradesongGame.skin));

        return layout;
    }

    @Override
    protected String getScreenName() {
        return workshop.getWorkshopName();
    }
}
