package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes items from thin air!
 */
public class ProducerWorkshop implements ItemCreator {

    private final Item itemProduced;
    /**
     * Set this to higher to do work less often
     */
    protected int turnsRequiredForWork = 1;
    List<Item> outputQueue = new ArrayList<Item>();
    private int turnsTakenSinceLastWork = 0;

    public ProducerWorkshop(Item itemProduced) {
        this.itemProduced = itemProduced;
    }

    public ProducerWorkshop(Item itemProduced, int turnsRequired) {
        this.itemProduced = itemProduced;
        this.turnsRequiredForWork = turnsRequired;
    }

    protected void doWork() {
        outputQueue.add(itemProduced);
    }

    @Override
    public boolean hasOutput() {
        return !outputQueue.isEmpty();
    }

    @Override
    public Item getNextOutput() {
        if (outputQueue.isEmpty()) {
            throw new IllegalStateException("Dev error, " + this.getClass().getName() + " was accessed when there was no output");
        }
        return outputQueue.remove(0);
    }

    @Override
    public String getWorkshopName() {
        return itemProduced.getName() + " Producer";
    }

    @Override
    public final void takeTurn() {
        turnsTakenSinceLastWork++;
        if (turnsTakenSinceLastWork >= turnsRequiredForWork) {
            doWork();
            turnsTakenSinceLastWork = 0;
        }
    }
}
