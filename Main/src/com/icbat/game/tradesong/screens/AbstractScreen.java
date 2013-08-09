package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Generic abstraction of things shared by all screens
 * */
public abstract class AbstractScreen implements Screen {

	protected Skin skin;
    protected SpriteBatch batch;

    public AbstractScreen() {
	}
	
	@Override
	public void render( float delta ) {
        render(delta, 0.2f, 0.2f, 0.2f, 1);

	}

    public void render (float delta, float r, float g, float b, float a) {
        Gdx.gl.glClearColor(r, g, b, a);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    }


	@Override
	public void dispose() {
        skin.dispose();

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

    }

    @Override
    public void show() {

    }
}
