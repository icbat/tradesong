package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
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
        final Table layout = new Table(TradesongGame.skin);
        layout.setFillParent(true);
        layout.align(Align.top);
        layout.add(workshop.getWorkshopName()).row();
        layout.add(new Label(" Workers:" + workshop.getWorkers().size(), TradesongGame.skin));

        return layout;
    }
}
