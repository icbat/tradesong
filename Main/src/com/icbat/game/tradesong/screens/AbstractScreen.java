package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.icbat.game.tradesong.Tradesong;

/**
 * Generic abstraction of things shared by all screens
 * */
public abstract class AbstractScreen implements Screen {



    protected final Tradesong gameInstance;
	protected Skin skin;
	protected Stage stage;
	protected SpriteBatch batch;
	
	public AbstractScreen( Tradesong game ) {
		this.gameInstance = game;
		log( "Creating" );
	}
	
	@Override
	public void render( float delta ) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	}

	@Override
	public void resize(int width, int height) {
		log( "Resizing to " + width + "w by " + height + "h" );
        stage.setViewport(width, height, false);
//		super.resize(width, height);

	}

	@Override
	public void show() {
		log( "Showing" );

	}

	@Override
	public void hide() {
		log( "Hiding" );

	}

	@Override
	public void pause() {
		log( "Pausing" );

	}

	@Override
	public void resume() {
		log( "Resuming" );

	}

	@Override
	public void dispose() {
		log( "Disposing" );
        stage.dispose();
        skin.dispose();

	}

    public Tradesong getGameInstance() {
        return gameInstance;
    }
	
	protected void log( String message ) {
		this.gameInstance.log.info(((Object) this).getClass().getSimpleName() + ":  " + message);
	}
    protected void log ( String descriptor, int i) {
        log (descriptor + ": " + new Integer(i).toString());
    }

}
