package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.RGBA;
import icbat.games.tradesong.engine.screens.components.*;

/***/
public abstract class AbstractBaseScreen implements Screen {
    protected final RGBA backgroundColor = new RGBA();
    private final Stage stage = new Stage();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buildStage();
        stage.draw();
        stage.act(delta);
    }

    private void buildStage() {
        stage.clear();
        final Table centralLayout = buildCentralLayout();
        centralLayout.setFillParent(true);
        stage.addActor(centralLayout);

        final Table statusBlock = buildStatusBlock();
        statusBlock.setFillParent(true);
        statusBlock.align(Align.top + Align.left);
        stage.addActor(statusBlock);

        final String headerText = getScreenName();
        final Table header = new Table(TradesongGame.skin);
        header.setFillParent(true);
        header.align(Align.top);
        header.add(headerText);
        stage.addActor(header);

        Table navigationHolder = new Table(TradesongGame.skin);
        navigationHolder.setFillParent(true);
        navigationHolder.align(Align.bottom + Align.left);
        navigationHolder.add(new GoToScreenButton("Overview", new OverviewScreen())).row().space(10);
        navigationHolder.add(new GoToScreenButton("Storage", new StorageScreen())).row().space(10);
        navigationHolder.add(new GoToScreenButton("Contracts", new ContractsScreen())).row().space(10);
        navigationHolder.add(new GoToScreenButton("Workshops", new WorkshopManagementScreen())).row().space(10);

        stage.addActor(navigationHolder);

        Table rarelyUsedButtons = new Table(TradesongGame.skin);
        rarelyUsedButtons.setFillParent(true);
        rarelyUsedButtons.align(Align.top + Align.right);
        rarelyUsedButtons.add(new EndTurnButton());

        stage.addActor(rarelyUsedButtons);
    }

    protected abstract String getScreenName();

    private Table buildStatusBlock() {
        final Table layout = new Table(TradesongGame.skin);
        layout.add(new TurnCounter(TradesongGame.turnTaker)).pad(20, 10, 10, 10).row();
        layout.add(new MoneyCounter(TradesongGame.holdings)).pad(10).row();
        layout.add(new SpareWorkerCounter(TradesongGame.holdings)).pad(10).row();
        return layout;
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("proto screen", "resized");
    }


    @Override
    public void show() {
        Gdx.app.debug("proto screen", "shown");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.app.debug("proto screen", "hidden");
    }

    @Override
    public void pause() {
        Gdx.app.debug("proto screen", "paused");
    }

    @Override
    public void resume() {
        Gdx.app.debug("proto screen", "resumed");
    }

    @Override
    public void dispose() {
        Gdx.app.debug("proto screen", "disposed");
    }

    protected abstract Table buildCentralLayout();
}
