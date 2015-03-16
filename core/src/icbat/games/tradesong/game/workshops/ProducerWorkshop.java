package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes items from thin air!
 */
public class ProducerWorkshop implements ItemProducer {

    private final Item itemProduced;
    /**
     * Set this to higher to do work less often
     */
    protected int turnsRequiredForWork = 1;
    List<Item> outputQueue = new ArrayList<Item>();
    private int turnsStoredUp = 0;
    private WorkerPool workerPool = new WorkerPoolImpl();

    public ProducerWorkshop(Item itemProduced) {
        this.itemProduced = itemProduced;
    }

    public ProducerWorkshop(Item itemProduced, int turnsRequired) {
        this.itemProduced = itemProduced;
        this.turnsRequiredForWork = turnsRequired;
    }

    protected void doWork() {
        outputQueue.add(itemProduced);
    }

    @Override
    public boolean hasOutput() {
        return !outputQueue.isEmpty();
    }

    @Override
    public Item getNextOutput() {
        if (outputQueue.isEmpty()) {
            throw new IllegalStateException("Dev error, " + this.getClass().getName() + " was accessed when there was no output");
        }
        return outputQueue.remove(0);
    }

    @Override
    public String getWorkshopName() {
        return itemProduced.getName() + " Producer";
    }

    @Override
    public WorkerPool getWorkers() {
        return this.workerPool;
    }

    @Override
    public final void takeTurn() {
        if (!workerPool.hasWorkers()) {
            return;
        }
        turnsStoredUp += determineTurnsToTake();
        while (turnsStoredUp >= turnsRequiredForWork) {
            doWork();
            turnsStoredUp -= turnsRequiredForWork;
        }
    }

    private int determineTurnsToTake() {
        final int size = workerPool.size();
        if (size == 1) {
            return 1;
        }

        int stepSize = 1;
        int output = 2;
        int count = 2;
        int stepsTaken = 0;
        while (count < workerPool.size()) {
            count++;
            stepsTaken++;
            if (stepsTaken == stepSize) {
                output++;
                stepsTaken = 0;
                stepSize++;
            }
        }

        return output;
    }

    @Override
    public Actor getActor() {
        Label.LabelStyle basicStyle = new Label.LabelStyle();
        basicStyle.font = new BitmapFont();
        return new Label(getWorkshopName(), basicStyle);
    }
}
