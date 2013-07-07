package com.icbat.game.tradesong;

import java.util.ArrayList;

/**
 * Basic class of all items/objects
 * */
public class Entity {

    private ArrayList<Component> components = new ArrayList<Component>();

    public Entity () {}

    /**
     * Preempting some sort of complexity/on-add events
     * */
    public void addComponent(Component comp) {
        components.add(comp);
    }

    /**
     * Preempting any on-remove events
     * */
    public void removeComponent(Component comp) {
        components.remove(comp);
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }
}
