package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/***/
public class OverviewScreen extends AbstractBaseScreen {

    public OverviewScreen() {
        backgroundColor.setGreen(0.3f);
    }

    @Override
    protected void buildStage() {
        stage.clear();
        final Table layout = buildLayout();
        stage.addActor(layout);
    }

    private Table buildLayout() {
        return new Table();
    }
}
