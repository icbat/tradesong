package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScreen implements Screen {

    protected List<BaseStage> stages = new ArrayList<BaseStage>();
    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    protected AbstractScreen() {}

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

    @Override
    public void hide() {
        for (BaseStage stage : stages) {
            stage.hide();
        }
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void resize(int width, int height) {
        for (BaseStage stage : stages) {
            stage.setViewport(width, height, false);
            stage.layout();
        }
    }

    @Override
    public void show() {
        setupInputMultiplexer();
    }

    protected void setupInputMultiplexer() {
        inputMultiplexer.clear();
        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public abstract String getScreenName();

    public void setupStages() {
        this.stages.clear();
        this.stages.add(new BaseStage() {
        });
    }
}
