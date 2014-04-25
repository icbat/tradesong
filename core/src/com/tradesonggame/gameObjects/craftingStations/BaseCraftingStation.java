package com.tradesonggame.gameObjects.craftingStations;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.TextureAssets;
import com.tradesonggame.screens.StationSummaryScreen;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseCraftingStation {

    String stationName = "";
    public String description = "";
    LinkedList<String> inputs = new LinkedList<String>();
    LinkedList<String> readyForOutput = new LinkedList<String>();
    public int iconX = 0;
    public int iconY = 0;


    public final String getStationName() {
        return stationName;
    }

    public final void ingest(List<String> possibleInputs) {

        for (int i=0; i < possibleInputs.size(); ++i) {
            if (isValidInput(possibleInputs.get(i))) {
                inputs.addLast(possibleInputs.remove(i));
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
    public final void process() {
        if (!this.inputs.isEmpty()) {
            this.readyForOutput.add(process(this.inputs.removeFirst()));
        }
    }

    protected abstract String process(String processedItem);

    public CraftingStationActor getActor() {
        CraftingStationActor craftingStationActor = new CraftingStationActor(this, iconX, iconY);
        craftingStationActor.addListener(getClickListener());
        return craftingStationActor;
    }
    private Actor getDescriptorPopup() { return new StationDescriptor(this); }

    public LinkedList<String> getReadyForOutput() {
        return readyForOutput;
    }

    public ClickListener getClickListener() {
        return new GoToStationDescriptorListener(this);
    }

    public Image getIcon() {
        return new Image(TextureAssets.ITEMS.getRegion(iconX, iconY));
    }

    public String getDescription() {
        return description;
    }

    public abstract Table getProcessDisplayTable();

    public static class CraftingStationActor extends Table {
        BaseCraftingStation backingNode;

        public CraftingStationActor(BaseCraftingStation backingNode, int iconX, int iconY) {
            super(Tradesong.uiStyles);
            this.backingNode = backingNode;
            NinePatchDrawable background = new NinePatchDrawable(new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2));
            this.setBackground(background);

            this.add(new Image(TextureAssets.ITEMS.getRegion(iconX,iconY))).pad(10);
            this.add(backingNode.getStationName()).padRight(10);

            this.setWidth(10);

            this.addListener(new StationPopupListener(backingNode.getDescriptorPopup()));

        }

        private class StationPopupListener extends ClickListener {
            private final Actor descriptorPopup;

            public StationPopupListener(Actor descriptorPopup) {
                this.descriptorPopup = descriptorPopup;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                Tradesong.focusedItem = descriptorPopup;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                Tradesong.focusedItem = null;
            }
        }
    }

    @Override
    public String toString() {
        return stationName;
    }

    private class StationDescriptor extends Table {
        public StationDescriptor(BaseCraftingStation station) {
            super(Tradesong.uiStyles);
            Label description = new Label(station.description, Tradesong.uiStyles);
            description.setWrap(true);
            NinePatch bgPatch = new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2);
            this.setBackground(new NinePatchDrawable(bgPatch));

            this.add(new Image(TextureAssets.ITEMS.getRegion(iconX, iconY))).prefWidth(32).space(5);
            this.add(station.stationName).prefWidth(125).space(5).row();
            this.add(description).colspan(2).prefWidth(125).space(5).spaceBottom(10).row();

            if (!inputs.isEmpty()) {
                this.add("Items awaiting processing").colspan(2).prefWidth(125).space(5).spaceBottom(10).row();
                addIconsFromList(station.inputs);
                this.row();
            }

            if (!readyForOutput.isEmpty()) {
                this.add("Finished items").colspan(2).prefWidth(125).space(5).spaceTop(10).row();
                addIconsFromList(station.readyForOutput);
                this.row();
            }
        }

        public void addIconsFromList(List<String> itemNames) {
            for (String itemName : itemNames) {
                this.add(Tradesong.items.getItem(itemName)).space(2);
            }
        }
    }

    private class GoToStationDescriptorListener extends ClickListener {

        private final BaseCraftingStation station;

        private GoToStationDescriptorListener(BaseCraftingStation station) {
            this.station = station;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            Tradesong.screenManager.goToScreen(new StationSummaryScreen(station));
        }
    }
}
