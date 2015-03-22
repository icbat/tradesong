package icbat.games.tradesong.game;

/***/
public class ItemStack {
    private final Item item;
    private int capacity;
    private int stackSize = 0;

    public ItemStack(Item item, int capacity) {
        this.item = item;
        if (capacity <= 0) {
            throw new IllegalStateException("Item stack for " + item.getName() + " cannot be created with capacity " + capacity);
        }
        this.capacity = capacity;
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
        if (!item.equals(input)) {
            throw new IllegalStateException("Dev error! This stack only accepts " + item.getName() + " but was handed " + input.getName());
        }
        if (isFull()) {
            throw new IllegalStateException("Dev error! Check stack's capacity before attempting to add!");
        }
        stackSize++;
    }

    public int getSize() {
        return stackSize;
    }

    public boolean isFull() {
        return stackSize >= capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
