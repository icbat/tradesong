package icbat.games.tradesong.game;

import java.util.ArrayList;
import java.util.List;

/***/
public class PlayerHoldings {
    private List<Item> storage = new ArrayList<Item>();
    private List workshops = new ArrayList<Workshop>();

    public void addWorkshop(Workshop workshop) {
        workshops.add(workshop);
    }

    public List<Item> getStorage() {
        return storage;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void storeItem(Item output) {
        storage.add(output);
    }

    public void removeWorkshop(Workshop workshop) {
        workshops.remove(workshop);
    }

    public int getStorageSize() {
        return storage.size();
    }

    public int getWorkshopSize() {
        return workshops.size();
    }
}
