package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseCraftingStation {

    String name = "";
    LinkedList<String> itemsBeingProcessed = new LinkedList<String>();
    LinkedList<String> readyForOutput = new LinkedList<String>();
    public int iconX = 0;
    public int iconY = 0;

    public final String getStationName() {
        return name;
    }

    public final void ingest(List<String> possibleInputs) {

        for (int i=0; i < possibleInputs.size(); ++i) {
            if (isValidInput(possibleInputs.get(i))) {
                itemsBeingProcessed.addLast(possibleInputs.remove(i));
            }
        }
    }

    public final String getNextOutput() {
        if (!readyForOutput.isEmpty()) {
            return readyForOutput.removeFirst();
        }
        return null;
    }

    public abstract boolean isValidInput(String inputItemName);
    public abstract void process();

    public CraftingStationActor getActor() {
        return new CraftingStationActor(this, iconX, iconY);
    }

    protected static class CraftingStationActor extends Table {
        BaseCraftingStation backingNode;

        public CraftingStationActor(BaseCraftingStation backingNode, int iconX, int iconY) {
            super(Tradesong.uiStyles);
            this.backingNode = backingNode;
            NinePatchDrawable background = new NinePatchDrawable(new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2));
            this.setBackground(background);

            this.add(new Image(TextureAssets.ITEMS.getRegion(iconX,iconY))).pad(10);
            this.add(backingNode.getStationName()).padRight(10);

        }
    }
}
