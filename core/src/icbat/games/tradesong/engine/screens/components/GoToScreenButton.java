package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;

/***/
public class GoToScreenButton extends TextButton {
    public GoToScreenButton(String displayText, Screen destination) {
        super(displayText, TradesongGame.skin);
        this.addListener(new GoToScreenListener(destination));
    }

    private class GoToScreenListener extends ClickListener {
        protected final Screen screen;

        public GoToScreenListener(Screen screen) {
            this.screen = screen;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            TradesongGame.screenManager.goToScreen(screen);
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
