package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class MoneyCounter extends Label {
    private final PlayerHoldings holdings;

    public MoneyCounter(PlayerHoldings holdings, LabelStyle labelStyle) {
        super("", labelStyle);
        this.holdings = holdings;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setText("Money: " + holdings.getCurrency());
        super.draw(batch, parentAlpha);
    }
}
