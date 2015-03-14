package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workshops.ItemConsumer;
import icbat.games.tradesong.game.workshops.ItemCreator;

import java.util.ArrayList;
import java.util.List;

/***/
public class PlayerHoldings {
    private List<Item> storage = new ArrayList<Item>();
    private List<Workshop> workshops = new ArrayList<Workshop>();
    private List<ItemCreator> producingWorkshops = new ArrayList<ItemCreator>();
    private List<ItemConsumer> consumingWorkshops = new ArrayList<ItemConsumer>();

    public void addWorkshop(Workshop workshop) {
        workshops.add(workshop);
        if (workshop instanceof ItemCreator) {
            producingWorkshops.add((ItemCreator) workshop);
        }

        if (workshop instanceof ItemConsumer) {
            consumingWorkshops.add((ItemConsumer) workshop);
        }
    }

    public List<Item> getStorage() {
        return storage;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public List<ItemCreator> getItemCreators() {
        return producingWorkshops;
    }

    public void storeItem(Item output) {
        storage.add(output);
    }

    public void removeWorkshop(Workshop workshop) {
        workshops.remove(workshop);
    }

    public List<ItemConsumer> getItemConsumers() {
        return consumingWorkshops;
    }
}
