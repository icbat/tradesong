package com.icbat.game.tradesong.screens.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.icbat.game.tradesong.screens.listeners.DragMoveListener;

public class MapStage extends Stage {
    private Actor dragCatcher = new Actor();

    public MapStage() {
        this.addActor(dragCatcher);
    }

    public void setDragListener(Camera camera) {
        Gdx.app.debug("", "Setting a drag listener");
        dragCatcher.clearListeners();
        dragCatcher.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        dragCatcher.setTouchable(Touchable.enabled);
        Gdx.app.debug("Drag Listeners", dragCatcher.getListeners().size + "");
        dragCatcher.addListener(new DragMoveListener(camera));
        Gdx.app.debug("Drag Listeners", dragCatcher.getListeners().size + "");

    }
}
