package com.icbat.game.tradesong;

public class StackedItems {
    private Item itemType;
    private int count = 0;

    public StackedItems(Item item) {
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

    public void add() {
        add(1);
    }

    public void add(int i) {
        count += i;
    }

    public void remove() {
        remove(1);
    }

    public void remove(int i) {
        count -= i;
    }
}
