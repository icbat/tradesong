package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.engine.screens.components.StorageDisplay;

/***/
public class StorageScreen extends AbstractBaseScreen {
    @Override
    protected String getScreenName() {
        return "Storage";
    }

    @Override
    protected Table buildCentralLayout() {
        return new StorageDisplay();
    }

}
