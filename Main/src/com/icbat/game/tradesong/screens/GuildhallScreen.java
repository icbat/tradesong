package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;
import com.icbat.game.tradesong.utils.ScreenMovingListener;
import com.icbat.game.tradesong.utils.ScreenTypes;

public class GuildhallScreen extends AbstractScreen {
    public GuildhallScreen (Tradesong gameInstance) {
        stages.add(new GuildhallStage(gameInstance));
    }


    class GuildhallStage extends AbstractStage {

        Table table = new Table();
        Tradesong gameInstance;

        GuildhallStage(Tradesong gameInstance) {
            this.gameInstance = gameInstance;
        }



        @Override
        public void layout() {
            this.clear();
            table.clearChildren();
            this.table.setFillParent(true);
            table.add(new Label("Guild hall", Tradesong.uiStyles.getLabelStyle()));
            table.row();
            table.add(newWorkshopButton());
            table.row();
            table.add(newContractsButton());
            table.row();
            table.add(newShopButton());
            table.row();
            table.add(newRentButton());

            this.addActor(table);
        }

        private Actor newContractsButton() {
            Actor textButton = new TextButton("Take and turn in Contracts", Tradesong.uiStyles.getTextButtonStyle());
            // TODO add screen and do this
            return textButton;
        }

        private Actor newWorkshopButton() {
            Actor textButton = new TextButton("Visit the Workshops", Tradesong.uiStyles.getTextButtonStyle());
            textButton.addListener(new ScreenMovingListener(ScreenTypes.WORKSHOP, gameInstance));
            return textButton;
        }



        private Actor newRentButton() {
            Actor textButton = new TextButton("Pay Rent", Tradesong.uiStyles.getTextButtonStyle());
            // TODO add screen and do this
            return textButton;
        }

        private Actor newShopButton() {
            Actor textButton = new TextButton("Visit Store", Tradesong.uiStyles.getTextButtonStyle());
            textButton.addListener(new ScreenMovingListener(ScreenTypes.STORE, gameInstance));
            return textButton;
        }
    }
}
