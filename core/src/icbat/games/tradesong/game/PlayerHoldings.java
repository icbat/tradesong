package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;
import icbat.games.tradesong.game.workshops.ItemConsumer;
import icbat.games.tradesong.game.workshops.ItemProducer;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***/
public class PlayerHoldings {
    private List<Workshop> workshops = new ArrayList<Workshop>();
    private Collection<ItemProducer> itemProducers = new ArrayList<ItemProducer>();
    private Collection<ItemConsumer> itemConsumers = new ArrayList<ItemConsumer>();
    private WorkerPool spareWorkers = new WorkerPoolImpl();
    private Storage storage = new Storage();
    private int currency = 0;


    public void addWorkshop(Workshop workshop) {
        workshops.add(workshop);
        if (workshop instanceof ItemProducer) {
            itemProducers.add((ItemProducer) workshop);
        }

        if (workshop instanceof ItemConsumer) {
            itemConsumers.add((ItemConsumer) workshop);
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public Collection<ItemProducer> getItemProducers() {
        return itemProducers;
    }

    public void removeWorkshop(Workshop workshop) {
        workshops.remove(workshop);
        if (workshop instanceof ItemProducer) {
            itemProducers.remove(workshop);
        }
        if (workshop instanceof ItemConsumer) {
            itemConsumers.remove(workshop);
        }
    }

    public Collection<ItemConsumer> getItemConsumers() {
        return itemConsumers;
    }

    public WorkerPool getSpareWorkers() {
        return spareWorkers;
    }

    public int getCurrency() {
        return currency;
    }

    public void addCurrency(int amount) {
        this.currency += amount;
    }

    public void removeCurrency(int amount) {
        this.currency -= amount;
    }
}
