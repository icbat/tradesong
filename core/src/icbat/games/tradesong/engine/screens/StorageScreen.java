package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.screens.components.AddWorkersButton;
import icbat.games.tradesong.engine.screens.components.RemoveWorkersButton;
import icbat.games.tradesong.engine.screens.components.StorageDisplay;

/***/
public class StorageScreen extends AbstractBaseScreen {
    @Override
    protected String getScreenName() {
        return "Storage";
    }

    @Override
    protected Table buildCentralLayout() {
        Table layout = new Table(TradesongGame.skin);
        layout.add("Items in storage:").space(15);
        layout.add("" + TradesongGame.holdings.getStorage().size()).space(15).row();

        layout.add("Workers assigned:").space(15);
        layout.add("" + TradesongGame.holdings.getStorage().getWorkers().size()).space(15).row();

        layout.add(new AddWorkersButton(TradesongGame.holdings.getStorage().getWorkers(), TradesongGame.holdings.getSpareWorkers())).space(15);
        layout.add(new RemoveWorkersButton(TradesongGame.holdings.getStorage().getWorkers(), TradesongGame.holdings.getSpareWorkers())).space(15).row();

        final StorageDisplay storageDisplay = new StorageDisplay();
        layout.add(storageDisplay).colspan(2).space(15);
        return layout;
    }

}
