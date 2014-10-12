package com.minus7games.tradesong.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
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
        Gdx.app.debug("Adding link between", input.getDisplayName() + " at " +input.getX() + " and " + output.getDisplayName() + " at " + output.getX());
    }

    @Override
    public Actor getActor() {
        Image image = new Image((com.badlogic.gdx.graphics.Texture) Tradesong.assets.get("1p.png"));
        image.setHeight(2);
        image.setPosition(input.getX() + input.getActor().getWidth() + PADDING, input.getY() - (image.getHeight() / 2) + (input.getActor().getHeight() / 2));
        image.setColor(Color.PINK);

        float deltaX = (output.getX() - input.getX()) - (input.getActor().getWidth()) - PADDING * 2;
        float deltaY = (output.getY() - input.getY());
        image.setWidth(calculateWidth(deltaX, deltaY));
        image.setRotation(calculateRotation(deltaX, deltaY));
        return image;
    }

    private float calculateRotation(float deltaX, float deltaY) {
        return (float) Math.toDegrees(Math.atan2(deltaY, deltaX));
    }

    private float calculateWidth(float deltaX, float deltaY) {
        Vector2 distance = new Vector2(deltaX, deltaY);

        return distance.len();
    }
}
