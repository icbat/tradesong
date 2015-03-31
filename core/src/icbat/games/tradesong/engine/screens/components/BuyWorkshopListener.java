package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workshops.Workshop;

/***/
public class BuyWorkshopListener extends ClickListener {
    private final Workshop workshop;

    public BuyWorkshopListener(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        final PlayerHoldings holdings = TradesongGame.holdings;
        if (workshop.canAfford(holdings.getCurrency())) {
            holdings.addWorkshop(workshop.spawnClone());
            holdings.removeCurrency(workshop.getCost());
        }
        return super.touchDown(event, x, y, pointer, button);
    }
}
