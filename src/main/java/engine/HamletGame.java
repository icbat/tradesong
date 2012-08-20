package main.java.engine;

import main.java.engine.layers.MainMenu;

public class HamletGame {

	public static void main(String[] args) {
		LayerStacker currentLayers = new LayerStacker(new MainWindow());
		
        currentLayers.push(new MainMenu());
    }
}
