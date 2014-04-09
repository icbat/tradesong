package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

import java.util.LinkedList;

public abstract class BaseCraftingStation {

    String name = "";
    LinkedList<String> itemsBeingProcessed = new LinkedList<String>();
    LinkedList<String> readyForOutput = new LinkedList<String>();

    public final String getStationName() {
        return name;
    }

    public final void ingest(String inputItemName) {
        if (isValidInput(inputItemName)) {
            itemsBeingProcessed.addLast(inputItemName);
        }
    }

    public final String extractOutput() {
        return readyForOutput.removeFirst();
    }

    public abstract boolean isValidInput(String inputItemName);
    public abstract void process();

    public CraftingStationActor getActor() {
        return new CraftingStationActor(this);
    }

    protected static class CraftingStationActor extends Table {
        BaseCraftingStation backingNode;

        public CraftingStationActor(BaseCraftingStation backingNode) {
            super(Tradesong.uiStyles);
            this.backingNode = backingNode;
            NinePatchDrawable background = new NinePatchDrawable(new NinePatch(Tradesong.getTexture(TextureAssets.SLIDER_BG), Color.BLACK));

            this.setBackground(background);
            this.add(backingNode.getStationName()).pad(10).space(10);

        }

        @Override
        public void draw(SpriteBatch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            this.debug();
        }
    }
}
