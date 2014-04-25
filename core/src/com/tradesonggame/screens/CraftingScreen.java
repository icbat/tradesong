package com.tradesonggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tradesonggame.Tradesong;
import com.tradesonggame.gameObjects.craftingStations.BaseCraftingStation;
import com.tradesonggame.screens.components.ColorOnHoverListener;

import java.util.ArrayList;
import java.util.List;

public class CraftingScreen extends BaseInGameScreen {

    private final Stage craftingStage;

    public CraftingScreen() {
        craftingStage = new Stage();
        setupStages(craftingStage);
        forceReload();
    }

    public void forceReload() {
        craftingStage.clear();
        craftingStage.addActor(new HolderTable());
    }

    @Override
    protected void doRenderWork() {
        drawBgColor(0.2f, 0.2f, 0.8f, 1);
    }

    @Override
    public String getScreenName() {
        return "craftingScreen";
    }

    private class HolderTable extends Table {
        private HolderTable() {
            super(Tradesong.uiStyles);
            this.setFillParent(true);
            this.add("Worskhop List").colspan(2).row();
//            LinkedList<BaseCraftingStation> currentShop = Tradesong.state.getWorkshopManager().get(0).getOrderedNodes();
//            ArrayList<BaseCraftingStation> allNodesCopy = Tradesong.craftingStations.getNodesCopy();
//            StationListing currentWorkshop = new StationListing("Current Setup", currentShop, allNodesCopy, true);
//            StationListing possibleStations = new StationListing("Available Stations", allNodesCopy, currentShop, false);

//            this.add(currentWorkshop).align(Align.left + Align.top).pad(5);
//            this.add(possibleStations).align(Align.left + Align.top).pad(5);
            this.pad(20);
        }
    }

    private class StationListing extends Table {
        private StationListing(String name, List<BaseCraftingStation> stations, List<BaseCraftingStation> linkedStationList, boolean isCurrentWorkshop) {
            super(Tradesong.uiStyles);
            this.add(name).colspan(3).row();
            for (BaseCraftingStation station : stations) {
                BaseCraftingStation.CraftingStationActor actor = station.getActor();
                actor.addListener(new ColorOnHoverListener(actor, Color.PINK));
                if (isCurrentWorkshop) {
                    Label up = new Label("Up", Tradesong.uiStyles);
                    Label down = new Label("Down", Tradesong.uiStyles);

                    up.addListener(new MoveUpListener(station, stations));
                    up.addListener(new ColorOnHoverListener(up, Color.PINK));
                    down.addListener(new MoveDownListener(station, stations));
                    down.addListener(new ColorOnHoverListener(down, Color.PINK));

                    this.add(up).space(3);
                    this.add(down).space(3);
                }

                if (!isCurrentWorkshop) {
                    Label swapButton = new Label("< Swap", Tradesong.uiStyles);
                    swapButton.addListener(new SwapListener(station, stations, linkedStationList, isCurrentWorkshop));
                    this.add(swapButton);
                }
                this.add(actor).align(Align.left).space(10).prefHeight(64).prefWidth(200);
                if (isCurrentWorkshop) {
                    Label swapButton = new Label("Swap >", Tradesong.uiStyles);
                    swapButton.addListener(new SwapListener(station, stations, linkedStationList, isCurrentWorkshop));
                    this.add(swapButton);
                }
                this.row();
            }
        }

        private List<BaseCraftingStation.CraftingStationActor> getStations() {
            List<BaseCraftingStation.CraftingStationActor> stations = new ArrayList<BaseCraftingStation.CraftingStationActor>();
            for (Actor actor : this.getChildren()) {
                if (actor instanceof BaseCraftingStation.CraftingStationActor) {
                    stations.add((BaseCraftingStation.CraftingStationActor) actor);
                }
            }

            return stations;
        }

        private class MoveUpListener extends ClickListener {
            private final BaseCraftingStation actor;
            private final List<BaseCraftingStation> backingList;

            public MoveUpListener(BaseCraftingStation actor, List<BaseCraftingStation> backingList) {
                this.actor = actor;
                this.backingList = backingList;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int i = backingList.indexOf(actor);
                Gdx.app.debug("i is", i +"");
                if (i > 0) {
                    Gdx.app.debug("before remove", backingList.toString());
                    BaseCraftingStation moving = backingList.remove(i);
                    Gdx.app.debug("after remove", backingList.toString());
                    Gdx.app.debug("adding at ", (i - 1) + "" );
                    backingList.add(i - 1, moving);
                    Gdx.app.debug("after add", backingList.toString());
                    forceReload();
                }
            }
        }

        private class MoveDownListener extends ClickListener {
            private final BaseCraftingStation actor;
            private final List<BaseCraftingStation> backingList;

            public MoveDownListener(BaseCraftingStation actor, List<BaseCraftingStation> backingList) {
                this.actor = actor;
                this.backingList = backingList;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int i = backingList.indexOf(actor);
                Gdx.app.debug("i is", i +"");
                if (i < backingList.size() - 1) {
                    Gdx.app.debug("before remove", backingList.toString());
                    BaseCraftingStation moving = backingList.remove(i);
                    Gdx.app.debug("after remove", backingList.toString());
                    Gdx.app.debug("adding at ", (i +1) + "" );
                    backingList.add(i + 1, moving);
                    Gdx.app.debug("after add", backingList.toString());
                    forceReload();
                }
            }
        }

        protected class SwapListener extends ClickListener {
            private final BaseCraftingStation station;
            private final List<BaseCraftingStation> stations;
            private final List<BaseCraftingStation> linkedStationList;
            private final boolean isCurrentWorkshop;

            public SwapListener(BaseCraftingStation station, List<BaseCraftingStation> stations, List<BaseCraftingStation> linkedStationList, boolean isCurrentWorkshop) {
                this.station = station;
                this.stations = stations;
                this.linkedStationList = linkedStationList;
                this.isCurrentWorkshop = isCurrentWorkshop;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.debug("is this the workshop?",isCurrentWorkshop +"");
                if (isCurrentWorkshop) {
                    stations.remove(station);
                } else {
                    linkedStationList.add(station);
                }
                forceReload();
            }
        }
    }
}
