package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.Tradesong;

public class InventoryScreen extends AbstractScreen {
	private String bgPath = "bg/bgrepeat.com.edited.jpg";
	
	// Disposables
	private Texture bg;
	public InventoryScreen(Tradesong instance) {
		super(instance);
		
		LJ.log("Loading background texture from: ", bgPath, LJ.DEBUG);
		bg = new Texture(Gdx.files.internal(bgPath));
		bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
	}
	
	@Override
	public void dispose() {
		bg.dispose();
	}

}
