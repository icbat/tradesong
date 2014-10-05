package com.minus7games.tradesong.workshops;

/** Wraps a CraftingStep with display information, like where it is when displayed and possibly what it's linked to? */
public class CraftingStepInUse {
    private final CraftingStep step;
    private float x;
    private float y;

    public CraftingStepInUse(CraftingStep step) {
        this.step = step;
    }

    public CraftingStep get() {
        return step;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
