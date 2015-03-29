package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/***/
public class BasicTextButton extends TextButton {
    public BasicTextButton(String text, TextButtonStyle style, ClickListener listener) {
        super(text, style);
        addListener(listener);
    }
}
