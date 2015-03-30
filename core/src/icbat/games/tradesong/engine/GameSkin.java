package icbat.games.tradesong.engine;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/***/
public class GameSkin extends Skin {
    public GameSkin() {
        this.add("default", new BitmapFont());
        final Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = this.getFont("default");
        this.add("default", labelStyle);
        final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = this.getFont("default");
        this.add("default", textButtonStyle);
    }
}
