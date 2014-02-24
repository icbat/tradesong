package com.icbat.game.tradesong.screens.dragAndDrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.screens.stages.BaseStage;

import java.util.List;

public class ItemSource extends DragAndDrop.Source{
    private List<Item> backingList;
    /** Redundant with this.actor, for ease of use interacting with other things. Must set in constructors to the actor (cast) */
    private final Item item;
    private final BaseStage owningStage;

    /**
     * If no backing list is provided, will assume it's backed by the inventory and remove from that when dropped.
     * */
    public ItemSource(Item item, BaseStage owningStage) {
        super(item);
        this.item = item;
        this.owningStage = owningStage;
    }

    public ItemSource(Item item, BaseStage owningStage, List<Item> backingList) {
        this(item, owningStage);
        this.backingList = backingList;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        DragAndDrop.Payload payload = new DragAndDrop.Payload();

        payload.setObject(getActor());
        payload.setDragActor(getActor());

        return payload;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Target target) {
        super.dragStop(event, x, y, pointer, target);
        if (target != null) {
            removeFromBackingList();
        }
        owningStage.layout();
    }

    private void removeFromBackingList() {

        if (backingList != null) {
            Gdx.app.debug("source; removing from ", backingList.toString());
            backingList.remove(this.item);
        } else { // It's backed by the inventory
            Gdx.app.debug("source; removing from ", "INVENTORY");
            Tradesong.inventory.takeOutItem(this.item);
        }
    }

}

