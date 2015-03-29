package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import icbat.games.tradesong.TradesongGame;

/***/
public abstract class BaseScreen implements Screen {
    protected Stage stage = new Stage();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buildStage();
        stage.draw();
        stage.act(delta);
    }

    protected abstract void buildStage();


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
