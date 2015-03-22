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
    private List<ItemProducer> producingWorkshops = new ArrayList<ItemProducer>();
    private List<ItemConsumer> consumingWorkshops = new ArrayList<ItemConsumer>();
    private WorkerPool spareWorkers = new WorkerPoolImpl();
    private Storage storage = new Storage();


    public void addWorkshop(Workshop workshop) {
        workshops.add(workshop);
        if (workshop instanceof ItemProducer) {
            producingWorkshops.add((ItemProducer) workshop);
        }

        if (workshop instanceof ItemConsumer) {
            consumingWorkshops.add((ItemConsumer) workshop);
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public Collection<ItemProducer> getItemCreators() {
        return producingWorkshops;
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

    public WorkerPool getSpareWorkers() {
        return spareWorkers;
    }
}
