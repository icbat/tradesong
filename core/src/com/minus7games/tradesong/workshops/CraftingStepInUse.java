package com.minus7games.tradesong.workshops;

/** Wraps a CraftingStep with display information, like where it is when displayed and possibly what it's linked to? */
public class CraftingStepInUse {
    private final CraftingStep step;
    private final float x;
    private final float y;

    public CraftingStepInUse(CraftingStep step, float x, float y) {
        this.step = step;

        this.x = x;
        this.y = y;
    }

    public CraftingStep get() {
        return step;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
