package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workshops.Workshop;

/***/
public class GoToScreenButton extends TextButton {
    public GoToScreenButton(Workshop workshop) {
        super("Go to " + workshop.getWorkshopName(), TradesongGame.skin);
        this.addListener(new GoToScreenListener(workshop));
    }

    private class GoToScreenListener extends ClickListener {
        private final Workshop workshop;

        public GoToScreenListener(Workshop workshop) {
            this.workshop = workshop;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            TradesongGame.screenManager.goToScreen(workshop.getScreen());
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
