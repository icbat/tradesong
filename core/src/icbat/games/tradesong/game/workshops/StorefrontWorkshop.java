package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    public WorkerPool getWorkers() {
        return workerPool;
    }

    @Override
    public String getWorkshopName() {
        return "Storefront";
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
    public StorefrontWorkshop spawnClone() {
        return new StorefrontWorkshop(holdings);
    }
}
