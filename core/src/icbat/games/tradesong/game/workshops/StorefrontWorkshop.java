package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.engine.screens.WorkshopScreen;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

import java.util.ArrayList;
import java.util.List;

/***/
public class StorefrontWorkshop implements ItemConsumer {
    private final PlayerHoldings holdings;
    private final List<Item> inputQueue = new ArrayList<Item>();
    protected WorkerPoolImpl workerPool = new WorkerPoolImpl();
    private int queueCapacity = 1;

    public StorefrontWorkshop(PlayerHoldings holdings) {
        this.holdings = holdings;
    }

    @Override
    public boolean acceptsInput(Item input) {
        return inputQueue.size() < queueCapacity;
    }

    @Override
    public void sendInput(Item input) {
        if (!acceptsInput(input)) {
            throw new IllegalStateException("Dev error, store cannot accept more items! Check acceptsInput first!");
        }
        inputQueue.add(input);
    }

    @Override
    public void updateInputQueueCapacity(int inputQueueCapacity) {
        this.queueCapacity = inputQueueCapacity;
    }

    @Override
    public void takeTurn() {
        Gdx.app.debug(getWorkshopName(), "Taking turn! Workers assigned: " + workerPool.size() + "; input queue size: " + inputQueue.size());
        if (!workerPool.hasWorkers()) {
            return;
        }
        int workersSpent = 0;
        while (workersSpent < workerPool.size() && !inputQueue.isEmpty()) {
            Item sold = inputQueue.remove(0);
            Gdx.app.debug(getWorkshopName(), "sold " + sold.getName() + ". now holding " + inputQueue.size());
            holdings.addCurrency(sold.getBasePrice());
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
        return layout;
    }

    @Override
    public StorefrontWorkshop spawnClone() {
        return new StorefrontWorkshop(holdings);
    }

    @Override
    public boolean canAfford(int currentFunds) {
        return currentFunds >= getCost();
    }

    @Override
    public int getCost() {
        return 5000;
    }

    @Override
    public Screen getScreen() {
        return new WorkshopScreen(this);
    }
}
