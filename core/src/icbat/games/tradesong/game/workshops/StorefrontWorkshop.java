package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.scenes.scene2d.Actor;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

/***/
public class StorefrontWorkshop implements ItemConsumer {
    private final PlayerHoldings holdings;
    protected WorkerPoolImpl workerPool = new WorkerPoolImpl();
    private int inputQueue = 0;
    private int queueCapacity = 1;

    public StorefrontWorkshop(PlayerHoldings holdings) {
        this.holdings = holdings;
    }

    @Override
    public boolean acceptsInput(Item input) {
        return inputQueue < queueCapacity;
    }

    @Override
    public void sendInput(Item input) {
        if (!acceptsInput(input)) {
            throw new IllegalStateException("Dev error, store cannot accept more items! Check acceptsInput first!");
        }
        inputQueue++;
    }

    @Override
    public void updateInputQueueCapacity(int inputQueueCapacity) {
        this.queueCapacity = inputQueueCapacity;
    }

    @Override
    public void takeTurn() {
        if (!workerPool.hasWorkers()) {
            return;
        }
        int workersSpent = 0;
        while (workersSpent < workerPool.size() && inputQueue > 0) {
            inputQueue--;
            holdings.addCurrency(100);
            workersSpent++;
        }
    }

    @Override
    public String getWorkshopName() {
        return null;
    }

    @Override
    public WorkerPool getWorkers() {
        return workerPool;
    }

    @Override
    public Actor getActor() {
        return null;
    }

    @Override
    public Workshop spawnClone() {
        return null;
    }
}
