package icbat.games.tradesong.game.workers;

/***/
public interface WorkerPool {
    void addWorker(Worker mock);

    Worker removeWorker();

    int size();

    boolean hasWorkers();
}
