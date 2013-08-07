package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.Workshop;

public class WorkshopStage extends Stage {

    private Workshop workshop;

    public WorkshopStage() {
        this(new Workshop());

    }

    public WorkshopStage(Workshop workshop) {
        super();
        this.workshop = workshop;
        addWorkshopTitle();
        addFrames();

    }

    private void addWorkshopTitle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        
        TextButton header = new TextButton(workshop.getType(), style);

    }
    private void addFrames() {

    }

    private Image makeIndividualFrame() {
        return null;

    }


}
