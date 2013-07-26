package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.Inventory;
import com.icbat.game.tradesong.StackedItem;
import com.icbat.game.tradesong.Tradesong;


// TODO Position stacks
// TODO make them clickable/draggable
// TODO show stack count

public class InventoryStage extends Stage {

    public InventoryStage(Tradesong gameInstance) {
        Inventory inventory = gameInstance.gameState.getInventory();

        for (int i = 0; i < inventory.capacity(); ++i) {
            if (i < inventory.size()) {
                addStackedItemToStage(inventory.getStack(i), i);
            }
        }

    }

    private void addStackedItemToStage(StackedItem stack, int position) {

    }


}
