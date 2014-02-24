package com.icbat.game.tradesong.screens.dragAndDrop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Item;

import java.util.List;

public class FrameTarget extends DragAndDrop.Target {

    private List<Item> backingList;
    private boolean validTarget;

    /**
     * If no backing list is provided, will assume it's backed by the inventory and remove from that when dropped.
     * */
    public FrameTarget(Actor frame, boolean validTarget) {
        super(frame);
        this.validTarget = validTarget;
    }

    public FrameTarget(Actor frame, boolean validTarget, List<Item> backingList) {
        this(frame, validTarget);
        this.backingList = backingList;
    }

    @Override
    /**
     * What happens when you drag something over this.
     * */
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        if (validTarget) {
            getActor().setColor(Color.GREEN);
        } else {
            getActor().setColor(Color.RED);
        }
        return validTarget;
    }

    @Override
    /**
     * What happens when you have already dragged on to this, and then you drag off of it.
     * */
    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
        super.reset(source, payload);
        getActor().setColor(Color.WHITE);
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        Item item = (Item) payload.getObject();
        if (backingList != null) {
            backingList.add(item);
        } else { // Assuming it's backed by Inventory
            Tradesong.inventory.addItem(item);
        }
    }
}
