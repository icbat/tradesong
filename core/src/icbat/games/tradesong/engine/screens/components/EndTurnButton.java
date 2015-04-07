package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;

/***/
public class EndTurnButton extends TextButton {
    public EndTurnButton() {
        super("End turn!", TradesongGame.skin);
        addListener(new EndTurnListener());
    }

    private class EndTurnListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            TradesongGame.turnTaker.takeAllTurns();
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
