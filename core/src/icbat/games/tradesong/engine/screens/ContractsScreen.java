package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.engine.screens.components.ContractsDisplay;

/***/
public class ContractsScreen extends AbstractBaseScreen {
    @Override
    protected String getScreenName() {
        return "Contracts";
    }

    @Override
    protected Table buildCentralLayout() {
        return new ContractsDisplay();
    }
}
