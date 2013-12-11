package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

/**
 * Basic, modular piece of just the inventory. Consider description area here or on its own stage (probably here)
 * */
public class InventoryStage extends Stage {
    public static final int FRAME_SIZE = 32;
    public static final int TOTAL_FRAMES = 30;
    private static final int SPACER = 8;
    public static final int HEIGHT_SPACER = 40;
    public static final int FRAMES_PER_ROW = 6;
    private Group frames = new Group();

    public InventoryStage() {
        this.frames.clear();
        addFrames();
        addDescriptionArea();
    }

    private void addFrames() {
        for (int i=0; i < TOTAL_FRAMES; ++i) {
            Image frame = new Image(Tradesong.getTexture(TextureAssets.FRAME));
            int x = SPACER + (i  % FRAMES_PER_ROW) * (FRAME_SIZE + SPACER);
            int y = (int) (HEIGHT_SPACER + (SPACER + FRAME_SIZE) * (Math.floor(i / FRAMES_PER_ROW)));
            frame.setPosition(x, y);
            frames.addActor(frame);

        }

        this.addActor(frames);
    }

    private void addDescriptionArea() {


    }
}
