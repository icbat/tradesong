package icbat.games.tradesong.game.workers;

import java.util.ArrayList;
import java.util.List;

/***/
public class WorkerPoolImpl implements WorkerPool {
    List<Worker> workers = new ArrayList<Worker>();

    @Override
    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    @Override
    public Worker removeWorker() {
        if (workers.isEmpty()) {
            throw new IllegalStateException("Dev error! Worker pool tried to remove a worker that didn't exist");
        }
        return workers.remove(0);
    }

    @Override
    public int size() {
        return workers.size();
    }

    @Override
    public boolean hasWorkers() {
        return !workers.isEmpty();
    }
}
