package com.icbat.game.tradesong.screens.listeners;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * Extends GDX's DragListener to implement drag to move on maps via camera movement.
 * */
public class DragMoveListener extends DragListener {

    final Camera camera;
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();
    private static final float DRAG_SPEED = 0.7f;

    public DragMoveListener(Camera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
        this.moveCameraBy(x, y);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        last.set(-1, -1, -1);
    }

    public void moveCameraBy(float x, float y) {
        // Use Camera1 as the last point
        curr.set(x, y, 0);

        // If this isn't the first drag called
        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            // Still use camera 1 as the latest; this time as change
            delta.set(last.x, last.y, 0);
            delta.sub(curr);
            camera.translate(delta.x * DRAG_SPEED, delta.y * DRAG_SPEED, 0);
        }

        last.set(x, y, 0);
    }


}
