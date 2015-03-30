package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;

/***/
public class BasicTextButton extends TextButton {
    public BasicTextButton(String text, ClickListener listener) {
        super(text, TradesongGame.skin);
        addListener(listener);
    }
}
