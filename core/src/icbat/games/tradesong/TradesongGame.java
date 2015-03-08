package icbat.games.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.engine.PrototypeScreen;

public class TradesongGame extends Game {
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		this.setScreen(new PrototypeScreen());
	}
}
