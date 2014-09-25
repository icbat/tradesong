package com.minus7games.tradesong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Tradesong extends Game {
    @Override
    public void create() {
        setupGame();
        sanityCheck();
        setScreen(new BaseScreen());
    }

    private void setupGame() {
        ItemIndex items = new ItemIndex(Gdx.files.internal("items.json"));
        NodeIndex nodes = new NodeIndex(Gdx.files.internal("nodes.json"));
    }

    private void sanityCheck() {
        Node orchard = NodeIndex.get("orchard");
        orchard.sendInput(ItemIndex.get("seed_apple"));
        orchard.act();
        Gdx.app.log("", orchard.getNextOutput().getDisplayName());
    }
}
