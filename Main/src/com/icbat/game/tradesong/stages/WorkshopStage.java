package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.Workshop;

public class WorkshopStage extends Stage {

    private Workshop workshop;
    private TextButton header;
    private Group frames = new Group();

    public WorkshopStage() {
        this(new Workshop("Blacksmith"));
    }

    public WorkshopStage(Workshop workshop) {
        super();
        setWorkshop(workshop); // Handles the standard setup

    }

    private void addWorkshopTitle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();

        header = new TextButton(workshop.getType(), style);
        header.setVisible(true);
        header.setTouchable(Touchable.disabled);
        header.setBounds(this.getWidth() - header.getWidth() - 20, this.getHeight() - header.getHeight(), header.getWidth(), header.getHeight());

        this.addActor(header);

    }
    private void addFrames() {
        frames.clearChildren();
        makeIndividualFrame();

    }

    private Image makeIndividualFrame() {
        return null;

    }

    public void setWorkshop(Workshop newWorkshop) {
        workshop = newWorkshop;

        if (header != null)
            header.remove();
        addWorkshopTitle();
        addFrames();
        addCraftButton();

    }

    private void addCraftButton() {


    }




}
