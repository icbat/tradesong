package main.java.engine;

import main.java.engine.screens.MainMenu;

public class Hamlet {

	public static void main(String[] args) {
		GameScreenManager screenManager = new GameScreenManager(new MainWindow());
        screenManager.push(new MainMenu());
        
        
    }
}
