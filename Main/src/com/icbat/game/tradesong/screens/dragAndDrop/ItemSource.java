package com.icbat.game.tradesong.screens.dragAndDrop;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.screens.stages.BaseStage;

/***/
public class ItemSource extends DragAndDrop.Source{
    private BaseStage owner;
    public ItemSource(Actor actor, BaseStage owner) {
        super(actor);
        this.owner = owner;
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
        owner.layout();
    }
}

