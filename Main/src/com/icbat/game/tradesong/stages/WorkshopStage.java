package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.Workshop;

public class WorkshopStage extends Stage {

    private Workshop workshop;
    private TextButton header;
    private Group frames = new Group();
    private Group ingredients = new Group();
    private Texture frameTexture;

    private static final int SPACER = 10;

    public WorkshopStage(Tradesong gameInstance) {
        this(gameInstance, new Workshop("Blacksmith"));
    }

    public WorkshopStage(Tradesong gameInstance, Workshop workshop) {
        super();
        frameTexture = gameInstance.assets.get(Tradesong.getFramePath());
        setWorkshop(workshop); // Handles the standard setup

    }

    private void addWorkshopTitle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();

        header = new TextButton(workshop.getType(), style);
        header.setVisible(true);
        header.setTouchable(Touchable.disabled);
        layOutVertically(header);

        this.addActor(header);

    }
    private void addFrames() {
        frames.clearChildren();

        for (int i = 0; i < workshop.getNumberOfSlots(); ++i) {
            Image frame = makeIndividualFrame();
            layOutVertically(frame);
            frame.setName(""+(Integer)i);
            frames.addActor(frame);
        }

        this.addActor(frames);


    }

    private Image makeIndividualFrame() {
        Image frameActor = new Image( new TextureRegion(frameTexture) );
        frameActor.setVisible(true);
        frameActor.setTouchable(Touchable.disabled);
        return frameActor;

    }

    public void setWorkshop(Workshop newWorkshop) {
        workshop = newWorkshop;

        if (header != null)
            header.remove();
        addWorkshopTitle();
        addFrames();
        addCraftButton();
        this.addActor(ingredients);

    }

    private void addCraftButton() {


    }

    /** Sets the bounds of the param to the next spot in a vertically descending pattern
     * @param   actor   actor on which to calculate bounds */
    private void layOutVertically(Actor actor) {
        actor.setBounds(this.getWidth() - actor.getWidth() - SPACER, findLowestY() - SPACER, actor.getWidth(), actor.getHeight());
    }

    private int findLowestY() {
        float lowestFound = this.getHeight() - 20;
        float check;
        for (Actor actor : this.getActors()) {
            check = actor.getY() - actor.getHeight();
            if (check < lowestFound)
                lowestFound = check;
        }

        for (Actor actor : frames.getChildren()) {
            check = actor.getY() - actor.getHeight();
            if (check < lowestFound)
                lowestFound = check;
        }

        return (int) lowestFound;

    }


    public boolean addIngredient(Item item) {

        // Check to see if there's space to add more
        Integer size = ingredients.getChildren().size;
        if (size >= workshop.getNumberOfSlots()) {
            return false;
        }
        else {

            Actor frame = frames.findActor(size.toString());
            item.setBounds(frame.getX(), frame.getY(), item.getWidth(), item.getHeight());

            // Add the listener to remove it
            item.addListener(new BackToInventoryClickListener());

            // Add the item
            ingredients.addActor(item);

            return true;
        }

    }


    class BackToInventoryClickListener extends ClickListener {

        BackToInventoryClickListener() {

        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            super.touchDown(event, x, y, pointer, button);

            return true;


        }
    }
}
