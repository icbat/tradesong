package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import icbat.games.tradesong.game.workers.WorkerPool;

/***/
public interface Workshop {
    void takeTurn();
    String getWorkshopName();
    WorkerPool getWorkers();
    Actor getActor();
    Workshop spawnClone();
    Screen getScreen();

    boolean canAfford(int currentFunds);

    int getCost();
}
