package icbat.games.tradesong.engine.screens;

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
    protected Table buildLayout() {
        return new Table(TradesongGame.skin);
    }
}
