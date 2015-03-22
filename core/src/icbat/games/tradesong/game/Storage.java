package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A warehouse?
 */
public class Storage {
    private List<Item> storage = new ArrayList<Item>();
    private WorkerPool workersAssignedToStorage = new WorkerPoolImpl();

    public List<Item> getContents() {
        return storage;
    }

    public WorkerPool getWorkersAssignedToStorage() {
        return workersAssignedToStorage;
    }

    public void storeItem(Item output) {
        storage.add(output);
    }

    public boolean contains(Item storedItem) {
        return storage.contains(storedItem);
    }

    public Item remove(Item storedItem) {
        int index = storage.indexOf(storedItem);
        return storage.remove(index);
    }
}
