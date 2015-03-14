package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.Workshop;

/***/
public interface ItemCreator extends Workshop {
    boolean hasOutput();

    Item getNextOutput();
}
