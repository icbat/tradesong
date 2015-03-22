package icbat.games.tradesong.game;

/***/
public class ItemStack {
    private final Item item;
    private int stackSize = 0;

    public ItemStack(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public Item removeItem() {
        if (stackSize <= 0) {
            throw new IllegalStateException("item stack for " + item.getName() + " attempted to drop below 0! Current size is " + stackSize);
        }
        stackSize--;
        return item.spawnClone();
    }

    public void addItem(Item input) {
        if (!item.isCloneOf(input)) {
            throw new IllegalStateException("Dev error! This stack only accepts " + item.getName() + " but was handed " + input.getName());
        }
        stackSize++;
    }

    public int getSize() {
        return stackSize;
    }
}
