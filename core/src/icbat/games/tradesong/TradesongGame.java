package icbat.games.tradesong;

import com.badlogic.gdx.Game;
import icbat.games.tradesong.engine.PrototypeScreen;

public class TradesongGame extends Game {
	
	@Override
	public void create () {
		this.setScreen(new PrototypeScreen());
	}
}
