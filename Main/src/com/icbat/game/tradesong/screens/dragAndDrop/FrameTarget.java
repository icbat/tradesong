package com.icbat.game.tradesong.screens.dragAndDrop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.screens.stages.BaseStage;

/**
 * Very generic target that gives minimal functionality.
 *
 * All methods should be safely super-able and they should be overloaded as necessary.
 * */
public class FrameTarget extends DragAndDrop.Target {

    private boolean validTarget;
    private BaseStage owner;

    public FrameTarget(Actor actor, boolean validTarget, BaseStage owner) {
        super(actor);
        this.validTarget = validTarget;
        this.owner = owner;
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
    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
        super.reset(source, payload);
        getActor().setColor(Color.WHITE);
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        owner.layout();
    }
}
