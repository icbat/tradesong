package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workshops.Workshop;

/***/
public class AddWorkshopListener extends ClickListener {
    private final Workshop workshop;

    public AddWorkshopListener(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        TradesongGame.holdings.addWorkshop(workshop.spawnClone());
        return super.touchDown(event, x, y, pointer, button);
    }
}
