package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;

/***/
public interface ItemProducer extends Workshop {
    /**
     * @return true if there is output available, false otherwise
     */
    boolean hasOutput();

    /**
     * Gets the next item
     *
     * @throws IllegalStateException If there is no item available, check with hasOutput() first!
     */
    Item getNextOutput();

    void updateOutputCapacity(int newCapacity);
}
