package com.icbat.game.tradesong;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<StackedItem> stacks = new ArrayList<StackedItem>();
    int capacity = 32;

    Inventory() {}

    public boolean add(Item newItem) {
        for(StackedItem stackedItem : stacks) {
            if (stackedItem.getItemType().getItemName().equals(newItem.getItemName())) {
                if (stackedItem.getCount() >= stackedItem.getItemType().maxStack) {
                    stackedItem.add();
                    return true;
                }
            }
        }

        if (stacks.size() >= capacity) {
            return false;
        }
        else {
            stacks.add(new StackedItem(newItem));
            return true;
        }


    }

    public Item itemAt(int i) {
        return stacks.get(i).getItemType();
    }

    public int size() {
        return stacks.size();
    }

}
