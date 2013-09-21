package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;

public class GuildhallScreen extends AbstractScreen {
    public GuildhallScreen() {
        stages.add(new GuildhallStage());
    }


    class GuildhallStage extends AbstractStage {

        Table table = new Table();

        @Override
        public void layout() {
            table.clearChildren();

            table.add("The Guildhall");
            table.row();
            table.add(newWorkshopButton());
            table.row();
            table.add(newShopButton());
            table.row();
            table.add(newRentButton());
        }

        private Actor newWorkshopButton() {
            Actor textButton = new TextButton("Workshops", Tradesong.uiStyles.getTextButtonStyle());

            return textButton;
        }

        private Actor newRentButton() {
            return null;
        }

        private Actor newShopButton() {
            return null;
        }
    }
}
