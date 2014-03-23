package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractScreen implements Screen {

    protected List<Stage> stages = new ArrayList<Stage>();
    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    protected AbstractScreen() {}

    @Override
    public void render(float delta) {
        drawBackground(0.1f, 0.1f, 0.1f, 1);
        renderStages(delta);
    }

    protected void renderStages(float delta) {
        for (Stage stage : stages) {
            stage.act(delta);
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
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void resize(int width, int height) {
        for (Stage stage : stages) {
            stage.setViewport(width, height, false);
        }
    }

    @Override
    public void show() {
        setupInputMultiplexer();
    }

    /** Stages are added highest level -> lowest level. Opposite of stage rendering */
    protected void setupInputMultiplexer() {
        inputMultiplexer.clear();
        List<Stage> stagesCopy = new LinkedList<Stage>(stages);
        Collections.reverse(stagesCopy);
        for (Stage stage : stagesCopy) {
            inputMultiplexer.addProcessor(stage);
        }
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public abstract String getScreenName();

    /** Stages are added lowest level -> highest level. Opposite of multiplexer */
    public void setupStages(Stage... extraStages) {
        for (Stage stage : stages) {
            stage.dispose();
        }
        this.stages.clear();
        Collections.addAll(this.stages, extraStages);
        this.stages.add(StageFactory.makeHUD());
    }
}
