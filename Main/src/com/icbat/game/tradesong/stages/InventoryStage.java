package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.icbat.game.tradesong.Inventory;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.StackedItem;
import com.icbat.game.tradesong.Tradesong;

import static com.badlogic.gdx.math.MathUtils.floor;

public class InventoryStage extends Stage {

    public static final int ICON_SIZE = 34;
    public static final int SLOT_SIZE = 32;
    public static final int SLOT_SPACING = 8;
    public static final int COLUMNS_PER_ROW = 5;

    private BitmapFont font = new BitmapFont();
    private final Texture frameTexture;

    public InventoryStage(Tradesong gameInstance) {
        Inventory inventory = gameInstance.gameState.getInventory();

        frameTexture = gameInstance.assets.get(Tradesong.getFramePath());

        // Add items
        for (int i = 0; i < inventory.capacity(); ++i) {

            int[] coords = positionToCoords(i);

            // Slot frames
            addSlotFrame(coords);

            if (i < inventory.size()) {
                addStackedItemToStage(inventory.getStack(i), coords);
                addItemCount(inventory.getStack(i), coords);
            }
        }


    }

    private void addStackedItemToStage(StackedItem stack, int[] position) {

        Item item = stack.getBaseItem();

        item.setBounds(position[0],position[1], ICON_SIZE, ICON_SIZE);
        item.setVisible(true);
        item.setTouchable(Touchable.enabled);

        this.addActor(item);
    }

    private void addItemCount(StackedItem stack, int[] position) {
        Integer stackSize = stack.getCount();

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        TextButton text = new TextButton(stackSize.toString(), buttonStyle);
        text.setTouchable(Touchable.disabled);
        text.setVisible(true);
        text.setBounds(position[0], position[1], ICON_SIZE, ICON_SIZE);

        this.addActor(text);


    }

    private void addSlotFrame(int[] position) {
        Image frameActor = makeSlotFrame();

        frameActor.setBounds(position[0],position[1], ICON_SIZE, ICON_SIZE);
        this.addActor(frameActor);

    }

    private Image makeSlotFrame() {
        Image frameActor = new Image( new TextureRegion(frameTexture));
        frameActor.setVisible(true);
        frameActor.setTouchable(Touchable.disabled);
        return frameActor;
    }

    /** Utility to take a list slot and take it to a 2d coordinate */
    private int[] positionToCoords(int position) {
        //boundary math
        int x;
        int y;

        int totalSlotSize = SLOT_SIZE + SLOT_SPACING;

        // Figure out how many cells are in each row/col
        x = position % COLUMNS_PER_ROW; // 5 columns
        y = floor(position / COLUMNS_PER_ROW);

        // Scale the Column and Row up by the png dimensions + a spacer
        x *= (totalSlotSize);
        y *= (totalSlotSize);

        // Start at the middle of the screen
        x += floor(this.getWidth()/2);
        y += floor(this.getHeight()/2);

        x -= (totalSlotSize * COLUMNS_PER_ROW / 2);
        //TODO center on y-axis

        int[] out = new int[2];
        out[0] = x;
        out[1] = y;

        return out;
    }

}
