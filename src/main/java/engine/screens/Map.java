package main.java.engine.screens;

import java.awt.Image;
import java.nio.file.Path;
import java.util.List;

import main.java.engine.GameScreen;
import main.java.engine.GameScreenManager;
import main.java.game.Entity;

public class Map implements GameScreen {
	private Image theMap;
	private List<Entity> entities;
	
	public Map () {
		
	}
	
	public Map (Path filename) {
		
	}
	
	@Override
	public void OnLoad(GameScreenManager sender) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnUnload(GameScreenManager sender) {
		// TODO Auto-generated method stub

	}

}
