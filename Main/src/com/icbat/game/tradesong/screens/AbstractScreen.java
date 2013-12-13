package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.screens.stages.BaseStage;
import com.icbat.game.tradesong.screens.stages.HUD;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic abstraction of things shared by all screens
 */
public abstract class AbstractScreen implements Screen {

    protected List<BaseStage> stages = new ArrayList<BaseStage>();
    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public AbstractScreen() {
        stages.add(new HUD());
    }

    public AbstractScreen(boolean addHud) {
        if (addHud) {
            stages.add(new HUD());
        }
    }

    @Override
    public void render(float delta) {
        drawBackground(0.1f, 0.1f, 0.1f, 1);
        renderStages(delta);
    }

    protected void renderStages(float delta) {
        for (BaseStage stage : stages) {
            stage.act(delta);
            stage.onRender();
            stage.draw();
        }
    }

    final void drawBackground(float r, float g, float b, float a) {
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
        for (BaseStage stage : stages) {
            stage.hide();
        }
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
        for (BaseStage stage : stages) {
            stage.setViewport(width, height, false);
            stage.layout();
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

    public abstract String getScreenName();
}
