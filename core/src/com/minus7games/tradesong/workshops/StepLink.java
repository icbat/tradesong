package com.minus7games.tradesong.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.minus7games.tradesong.Displayable;
import com.minus7games.tradesong.Tradesong;

/** Links input and output of two steps. */
public class StepLink implements Displayable {
    public static final int PADDING = 5;
    private final CraftingStep input;
    private final CraftingStep output;

    public StepLink(CraftingStep input, CraftingStep output) {
        this.input = input;
        this.output = output;
        Gdx.app.log("Adding link between", input.getDisplayName() + " at " +input.getX() + " and " + output.getDisplayName() + " at " + output.getX());
    }

    @Override
    public Actor getActor() {
        Image image = new Image((com.badlogic.gdx.graphics.Texture) Tradesong.assets.get("1p.png"));
        image.setHeight(2);
        image.setWidth(calculateWidth());
        image.setPosition(input.getX() + input.getActor().getWidth() + PADDING, input.getY() - (image.getHeight() / 2) + (input.getActor().getHeight() / 2));
        image.setRotation(calculateRotation());
        return image;
    }

    private float calculateRotation() {
        float deltaX = (output.getX() - input.getX());
        float deltaY = (output.getY() - input.getY());

        final float rotation = (float) Math.toDegrees(Math.atan2(deltaY, deltaX));
        Gdx.app.log("rotation found at degrees", rotation + "");
        return rotation;
    }

    private float calculateWidth() {
        return 100;
    }
}
