package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class SpareWorkerCounter extends Label {
    private final PlayerHoldings holdings;

    public SpareWorkerCounter(PlayerHoldings holdings) {
        super("", TradesongGame.skin);
        this.holdings = holdings;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setText("Spare workers: " + holdings.getSpareWorkers().size());
        super.draw(batch, parentAlpha);
    }
}
