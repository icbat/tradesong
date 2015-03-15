package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.engine.Displayable;

/***/
public interface Workshop extends Displayable {
    void takeTurn();

    String getWorkshopName();
}
