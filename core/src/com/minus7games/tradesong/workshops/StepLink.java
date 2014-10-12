package com.minus7games.tradesong.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.minus7games.tradesong.Displayable;

/** Links input and output of two steps. */
public class StepLink implements Displayable {
    private final CraftingStep input;
    private final CraftingStep output;

    public StepLink(CraftingStep input, CraftingStep output) {
        this.input = input;
        this.output = output;
        Gdx.app.log("step added between", input.getDisplayName() + " and " + output.getDisplayName());
    }

    @Override
    public Actor getActor() {
        return null;
    }
}
