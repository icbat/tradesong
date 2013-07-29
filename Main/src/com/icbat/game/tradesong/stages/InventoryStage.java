package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.icbat.game.tradesong.Inventory;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.StackedItem;
import com.icbat.game.tradesong.Tradesong;

import static com.badlogic.gdx.math.MathUtils.floor;


// TODO Position stacks
// TODO make them clickable/draggable
// TODO show stack count

public class InventoryStage extends Stage {

    public static final int SIZE = 34;
    public static final int SLOT_SIZE = 40;

    public InventoryStage(Tradesong gameInstance) {
        Inventory inventory = gameInstance.gameState.getInventory();

        gameInstance.assets.load("sprites/frame.png", Texture.class);

        // Slot frames
        for (int i = 0; i < inventory.capacity(); ++i) {
            addSlotFrame(i);
        }

        // Add items
        for (int i = 0; i < inventory.capacity(); ++i) {
            gameInstance.log.info("## Checking");
            if (i < inventory.size()) {
                gameInstance.log.info("## Adding an item");
                addStackedItemToStage(inventory.getStack(i), i);
            }
        }

    }

    private void addStackedItemToStage(StackedItem stack, int position) {

        Item item = stack.getBaseItem(); // Item is an Actor and meant to be representative, just like this

        //boundary math
        int x;
        int y;

        x = position % 5; // 5 columns
        y = floor(position / 5);

        x *= SLOT_SIZE;
        y *= SLOT_SIZE;

        //TODO I'd rather do something based on height/width but this is fine for now
        x += 100;
        y += 100;

        item.setBounds(x,y, SIZE, SIZE);
        item.setVisible(true);
        item.setTouchable(Touchable.enabled);
        this.addActor(item);
    }

    private void addSlotFrame(int position) {

    }


}
