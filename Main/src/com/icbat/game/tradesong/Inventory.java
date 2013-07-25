package com.icbat.game.tradesong;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<StackedItem> stacks = new ArrayList<StackedItem>();
    int capacity = 32;

    Inventory() {}

    public boolean add(Item newItem) {
        for(StackedItem stackedItem : stacks) {
            // If we found a non-full stack
            if (stackedItem.getBaseItem().getItemName().equals(newItem.getItemName())) {
                // See if we can add to it
                if (stackedItem.add()) {
                    // Note: this needs to be return true; and not return stackedItem.add();
                    //       This is because if it's false, we don't want to quit looking, we want to check the next stack, and so on.
                    return true;
                }
            }
        }

        // We didn't find a stack. Can we make a new one?
        if (stacks.size() >= capacity) {
            // Nope. We're already full.
            return false;
        }
        else {
            // Sure! Add a new one.
            stacks.add(new StackedItem(newItem));
            return true;
        }


    }

    public Item itemAt(int i) {
        return stacks.get(i).getBaseItem();
    }

    public int size() {
        return stacks.size();
    }

}
