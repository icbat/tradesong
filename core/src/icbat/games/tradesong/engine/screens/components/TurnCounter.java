package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import icbat.games.tradesong.game.TurnTaker;

/***/
public class TurnCounter extends Label {
    private final TurnTaker turnTaker;

    public TurnCounter(TurnTaker turnTaker, LabelStyle labelStyle) {
        super("", labelStyle);
        this.turnTaker = turnTaker;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setText("Turn count: " + turnTaker.getCurrentTurn());
        super.draw(batch, parentAlpha);
    }
}
