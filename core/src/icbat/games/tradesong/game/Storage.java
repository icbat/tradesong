package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workers.WorkerImpl;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A warehouse?
 */
public class Storage {
    private List<Item> itemsStored = new ArrayList<Item>();
    private WorkerPool workersAssignedToStorage = new WorkerPoolImpl();

    public Storage() {
        workersAssignedToStorage.addWorker(new WorkerImpl());
    }

    public List<Item> getContents() {
        return itemsStored;
    }

    public WorkerPool getWorkers() {
        return workersAssignedToStorage;
    }

    public void storeItem(Item output) {
        itemsStored.add(output);
    }

    public boolean contains(Item storedItem) {
        return itemsStored.contains(storedItem);
    }

    public Item remove(Item storedItem) {
        if (!itemsStored.contains(storedItem)) {
            throw new IllegalStateException("Dev error, tried to pull a " + storedItem.getName() + " from storage that didn't exist!");
        }
        int index = itemsStored.indexOf(storedItem);
        return itemsStored.remove(index);
    }

    public boolean isEmpty() {
        return itemsStored.isEmpty();
    }

    public int size() {
        return itemsStored.size();
    }
}
