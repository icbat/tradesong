package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.Item;

/***/
public class StorageDisplay extends Table {

    public StorageDisplay() {
        super(TradesongGame.skin);

        for (Item item : TradesongGame.holdings.getStorage().getContents()) {
            add(item.getActor()).row();
        }
    }
}
