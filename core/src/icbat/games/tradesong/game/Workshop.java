package icbat.games.tradesong.game;

import icbat.games.tradesong.engine.Displayable;

/***/
public interface Workshop extends Displayable {
    void takeTurn();

    String getWorkshopName();
}
