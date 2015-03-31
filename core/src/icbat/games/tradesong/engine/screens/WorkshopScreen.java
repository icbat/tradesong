package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.screens.components.AddWorkersButton;
import icbat.games.tradesong.engine.screens.components.RemoveWorkersButton;
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
        layout.add(new Label("Assigned Workers:" + workshop.getWorkers().size(), TradesongGame.skin)).colspan(2).row();
        layout.add(new AddWorkersButton(workshop, TradesongGame.holdings.getSpareWorkers())).pad(15);
        layout.add(new RemoveWorkersButton(workshop, TradesongGame.holdings.getSpareWorkers())).pad(15);
        return layout;
    }

    @Override
    protected String getScreenName() {
        return workshop.getWorkshopName();
    }
}
