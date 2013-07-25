package com.icbat.game.tradesong;

public class StackedItem {
    private Item itemType;
    private int count = 0;

    public StackedItem(Item item) {
        itemType = item;
    }

    public Item getItemType() {
        return itemType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /** Where the magic happens */
    public void add(int i) {
        count += i;
    }

    public void add() {
        add(1);
    }

    public void remove() {
        add(-1);
    }

    public void remove(int i) {
        add(-1 * i);
    }
}
