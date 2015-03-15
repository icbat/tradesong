package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.Workshop;

/***/
public interface ItemConsumer extends Workshop {
    /**
     * @return true if this workshop accepts the given item; otherwise false
     */
    boolean acceptsInput(Item input);

    /**
     * Consumes the given item.
     *
     * @throws IllegalStateException if the item is unacceptable, check with acceptsInput() first!
     */
    void sendInput(Item input);
}
