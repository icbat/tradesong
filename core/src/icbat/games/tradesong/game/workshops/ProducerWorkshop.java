package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Workshop;

import java.util.ArrayList;
import java.util.List;

public class ProducerWorkshop implements Workshop {

    protected String itemProduced = "an item";
    List<String> outputQueue = new ArrayList<String>();

    @Override
    public void takeTurn() {
        outputQueue.add(itemProduced);
    }

    @Override
    public boolean hasOutput() {
        return !outputQueue.isEmpty();
    }

    @Override
    public String getNextOutput() {
        if (outputQueue.isEmpty()) {
            throw new IllegalStateException("Dev error, " + this.getClass().getName() + " was accessed when there was no output");
        }
        return outputQueue.remove(0);
    }
}
