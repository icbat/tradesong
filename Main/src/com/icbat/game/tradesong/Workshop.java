package com.icbat.game.tradesong;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/** Abstraction/data structure for what a workshop can do/knows */
public class Workshop {
    private String type;
    private int numberOfSlots;
    private Image workshopIcon;


    public Workshop(String workshop) {
        type = workshop;
        numberOfSlots = 3; // arbitrary default, not even sure if this should ever change
    }

    /** Copy constructor */
    public Workshop(Workshop old) {
        this(old.getType());
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workshop workshop = (Workshop) o;

        if (numberOfSlots != workshop.numberOfSlots) return false;
        if (!type.equals(workshop.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + numberOfSlots;
        return result;
    }
}
