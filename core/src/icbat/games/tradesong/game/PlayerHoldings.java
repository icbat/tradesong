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
    private List<Item> storage = new ArrayList<Item>();
    private List<Workshop> workshops = new ArrayList<Workshop>();
    private List<ItemProducer> producingWorkshops = new ArrayList<ItemProducer>();
    private List<ItemConsumer> consumingWorkshops = new ArrayList<ItemConsumer>();
    private WorkerPool spareWorkers = new WorkerPoolImpl();

    public void addWorkshop(Workshop workshop) {
        workshops.add(workshop);
        if (workshop instanceof ItemProducer) {
            producingWorkshops.add((ItemProducer) workshop);
        }

        if (workshop instanceof ItemConsumer) {
            consumingWorkshops.add((ItemConsumer) workshop);
        }
    }

    public Collection<Item> getStorageContents() {
        return storage;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public Collection<ItemProducer> getItemCreators() {
        return producingWorkshops;
    }

    public void storeItem(Item output) {
        storage.add(output);
    }

    public void removeWorkshop(Workshop workshop) {
        workshops.remove(workshop);
        if (workshop instanceof ItemProducer) {
            producingWorkshops.remove(workshop);
        }
        if (workshop instanceof ItemConsumer) {
            consumingWorkshops.remove(workshop);
        }
    }

    public Collection<ItemConsumer> getItemConsumers() {
        return consumingWorkshops;
    }

    public Item removeFromStorage(Item storedItem) {
        if (!storage.contains(storedItem)) {
            throw new IllegalStateException("Dev error, tried to pull a " + storedItem.getName() + " from storage that didn't exist!");
        }
        int foundIndex = storage.indexOf(storedItem);
        return storage.remove(foundIndex);
    }

    public WorkerPool getSpareWorkers() {
        return spareWorkers;
    }
}
