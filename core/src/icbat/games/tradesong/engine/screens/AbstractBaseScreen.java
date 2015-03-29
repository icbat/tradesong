package icbat.games.tradesong.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.engine.RGBA;

/***/
public abstract class AbstractBaseScreen implements Screen {
    protected final Stage stage = new Stage();
    protected final RGBA backgroundColor = new RGBA();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buildStage();
        stage.draw();
        stage.act(delta);
    }

    /**
     * Called on each render, rebuilds the stage. Really inefficient, there's probably a better way but for now this will do.
     *
     * Given that, you'll usually want to call stage.clear first
     */
    protected abstract void buildStage();


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
