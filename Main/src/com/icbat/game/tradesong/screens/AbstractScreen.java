package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.icbat.game.tradesong.stages.AbstractStage;

import java.util.ArrayList;

/**
 * Generic abstraction of things shared by all screens
 * */
public abstract class AbstractScreen implements Screen {

	protected Skin skin;
    protected SpriteBatch batch;
    protected ArrayList<AbstractStage> stages = new ArrayList<AbstractStage>();
    InputMultiplexer inputMultiplexer;

    public AbstractScreen() {
        inputMultiplexer = new InputMultiplexer();

    }
	
	@Override
	public void render( float delta ) {
        render(delta, 0.2f, 0.2f, 0.2f, 1);

	}

    public void render (float delta, float r, float g, float b, float a) {
        Gdx.gl.glClearColor(r, g, b, a);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        for (AbstractStage stage : stages) {
            stage.act(delta);
            stage.draw();
        }

    }


	@Override
	public void dispose() {
        skin.dispose();
        for (AbstractStage stage : stages) {
            stage.dispose();
        }

	}


    @Override
    public String toString() {
        return ((Object) this).getClass().getSimpleName();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {
        for (AbstractStage stage : stages) {
            stage.setViewport(width,height, false);
            stage.layout();
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);

    }
}
