package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;

/**
 * Takes one more more items as input and outputs 1 new item
 */
public class MutatorWorkshop extends DelayedWorkshop implements ItemCreator, ItemConsumer {

    @Override
    protected void doWork() {

    }


    @Override
    public String getWorkshopName() {
        return null;
    }

    @Override
    public boolean hasOutput() {
        return false;
    }

    @Override
    public Item getNextOutput() {
        return null;
    }
}
