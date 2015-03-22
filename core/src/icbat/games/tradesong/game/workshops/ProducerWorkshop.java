package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.ItemStack;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

/**
 * Makes items from thin air!
 */
public class ProducerWorkshop implements ItemProducer {

    private final Item itemProduced;
    private final ItemStack outputQueue;
    /**
     * Set this to higher to do work less often
     */
    protected int turnsRequiredForWork;
    private int turnsStoredUp = 0;
    private WorkerPool workerPool = new WorkerPoolImpl();

    public ProducerWorkshop(Item itemProduced) {
        this(itemProduced, 1);
    }

    public ProducerWorkshop(Item itemProduced, int turnsRequired) {
        this.itemProduced = itemProduced;
        this.turnsRequiredForWork = turnsRequired;
        this.outputQueue = new ItemStack(itemProduced, 1);
    }

    protected void doWork() {
        outputQueue.addItem(itemProduced);
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
        return outputQueue.removeItem();
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
        while (turnsStoredUp >= turnsRequiredForWork && !outputQueue.isFull()) {
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
        Table layout = new Table();
        Label.LabelStyle basicStyle = new Label.LabelStyle();
        basicStyle.font = new BitmapFont();
        layout.add(new Label(getWorkshopName(), basicStyle)).row();
        layout.add(new Label(" Workers:" + getWorkers().size(), basicStyle));
        return layout;
    }

    @Override
    public ProducerWorkshop spawnClone() {
        return new ProducerWorkshop(itemProduced.spawnClone());
    }

    public void updateOutputCapacity(int newCapacity) {
        outputQueue.setCapacity(newCapacity);
    }
}
