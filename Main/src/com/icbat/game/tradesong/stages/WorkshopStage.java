package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.Workshop;

public class WorkshopStage extends Stage {

    private Tradesong gameInstance;
    private Workshop workshop;
    private TextButton header;

    public WorkshopStage(Tradesong gameInstance) {
        this(gameInstance, new Workshop(gameInstance.gameState.getWorkshopByName("Blacksmith")));
    }

    public WorkshopStage(Tradesong gameInstance, Workshop workshop) {
        super();
        this.gameInstance = gameInstance;
        this.workshop = workshop;
        addWorkshopTitle();
        addFrames();

    }

    private void addWorkshopTitle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();

        header = new TextButton(workshop.getType(), style);

        header.setVisible(true);
        header.setTouchable(Touchable.disabled);
        header.setBounds(this.getWidth() - header.getWidth() - 20, this.getHeight() - header.getHeight(), header.getWidth(), header.getHeight());


    }
    private void addFrames() {

    }

    private Image makeIndividualFrame() {
        return null;

    }


}
