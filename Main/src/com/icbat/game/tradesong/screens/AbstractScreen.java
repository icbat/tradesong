package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic abstraction of things shared by all screens
 */
public abstract class AbstractScreen implements Screen {

    protected List<Stage> stages = new ArrayList<Stage>();
    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public AbstractScreen() {
    }

    @Override
    public void render(float delta) {
        drawBackground(delta, 0.1f, 0.1f, 0.1f, 1);
        renderStages(delta);
    }

    protected void renderStages(float delta) {
        for (Stage stage : stages) {
            stage.act(delta);
            stage.draw();
        }
    }

    final void drawBackground(float delta, float r, float g, float b, float a) {
        Gdx.gl.glClearColor(r, g, b, a);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        for (Stage stage : stages) {
            stage.dispose();
        }

    }

    /**
     * This method does nothing, but must be here to allow the children to not implement this.
     */
    @Override
    public void hide() {
    }

    /**
     * This method does nothing, but must be here to allow the children to not implement this.
     */
    @Override
    public void pause() {

    }

    /**
     * This method does nothing, but must be here to allow the children to not implement this.
     */
    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {
        for (Stage stage : stages) {
            stage.setViewport(width, height, false);
        }
    }

    @Override
    public void show() {
        inputMultiplexer.clear();
        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }

        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
