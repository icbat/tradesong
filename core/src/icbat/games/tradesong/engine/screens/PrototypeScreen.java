package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.PrototypeLayoutTable;

public class PrototypeScreen implements Screen {

    private Stage stage = new Stage();

    public PrototypeScreen() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buildStage();
        stage.draw();
        stage.act(delta);
    }

    private void buildStage() {
        stage.clear();
        final Table layout = new PrototypeLayoutTable(TradesongGame.turnTaker, TradesongGame.potentialWorkshops, TradesongGame.holdings, TradesongGame.contracts);
        stage.addActor(layout);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("proto screen", "resized");
    }

    @Override
    public void show() {
        Gdx.app.debug("proto screen", "shown");
    }

    @Override
    public void hide() {
        Gdx.app.debug("proto screen", "hidden");
        TradesongGame.turnTimer.stop();
    }

    @Override
    public void pause() {
        Gdx.app.debug("proto screen", "paused");
        TradesongGame.turnTimer.stop();
    }

    @Override
    public void resume() {
        Gdx.app.debug("proto screen", "resumed");
        TradesongGame.turnTimer.start();
    }

    @Override
    public void dispose() {
        Gdx.app.debug("proto screen", "disposed");
        TradesongGame.turnTimer.stop();
        TradesongGame.turnTimer.clear();
    }
}
