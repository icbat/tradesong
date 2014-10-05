package com.minus7games.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

/***/
public class BaseScreen implements Screen {
    public static final String BASE_SCREEN = "base screen";
    protected Stage stage = new Stage();
    @Override
    public void render(float delta) {
        actStep(delta);
        renderStep(delta);
        postRenderStep(delta);
    }

    protected void actStep(float delta) {
        stage.act(delta);
    }

    protected void renderStep(float delta) {
        stage.draw();
    }

    protected void postRenderStep(float delta) {}

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug(BASE_SCREEN, "resized to " + width + " wide by " + height + " high");
        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        Gdx.app.debug(BASE_SCREEN, "shown");
    }

    @Override
    public void hide() {
        Gdx.app.debug(BASE_SCREEN, "hidden");
    }

    @Override
    public void pause() {
        Gdx.app.debug(BASE_SCREEN, "paused");
    }

    @Override
    public void resume() {
        Gdx.app.debug(BASE_SCREEN, "resumed");
    }

    @Override
    public void dispose() {
        Gdx.app.debug(BASE_SCREEN, "disposed");
        stage.dispose();
    }
}
