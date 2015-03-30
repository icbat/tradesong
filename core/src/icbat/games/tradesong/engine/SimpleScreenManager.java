package icbat.games.tradesong.engine;

import com.badlogic.gdx.Screen;
import icbat.games.tradesong.TradesongGame;

/***/
public class SimpleScreenManager implements ScreenManager {
    private final TradesongGame game;

    public SimpleScreenManager(TradesongGame game) {
        this.game = game;
    }

    @Override
    public void goToScreen(Screen destination) {
        game.setScreen(destination);
    }
}
