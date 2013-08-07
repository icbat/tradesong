package com.icbat.game.tradesong;

/** Abstraction/data structure for what a workshop can do/knows */
public class Workshop {
    private String type;
    private int numberOfSlots;

    public Workshop(String workshop) {

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
