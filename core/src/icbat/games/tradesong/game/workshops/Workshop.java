package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.engine.Displayable;
import icbat.games.tradesong.game.workers.WorkerPool;

/***/
public interface Workshop extends Displayable {
    void takeTurn();
    String getWorkshopName();

    WorkerPool getWorkers();
}
