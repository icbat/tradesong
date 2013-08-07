package com.icbat.game.tradesong;

/** Abstraction/data structure for what a workshop can do/knows */
public class Workshop {
    private String type;
    private int numberOfSlots;

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public String getType() {
        return type;
    }
}
