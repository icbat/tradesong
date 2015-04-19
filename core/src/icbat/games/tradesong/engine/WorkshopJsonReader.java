package icbat.games.tradesong.engine;

import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workshops.MutatorWorkshop;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;
import icbat.games.tradesong.game.workshops.StorefrontWorkshop;
import icbat.games.tradesong.game.workshops.Workshop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***/
public class WorkshopJsonReader {
    public Collection<Workshop> read(String rawJson) {
        JSONArray json = new JSONArray(rawJson);
        Collection<Workshop> workshops = new ArrayList<Workshop>();

        for (int i = 0; i < json.length(); ++i) {
            JSONObject workshop = json.getJSONObject(i);
            String type = workshop.getString("workshopType");

            if ("STOREFRONT".equals(type)) {
                workshops.add(new StorefrontWorkshop(TradesongGame.holdings));
                continue;
            }

            Item outputItem = findItemByName(workshop.getString("outputItemName"));
            int baseTimeRequired = getBaseTimeRequired(workshop);
            if ("PRODUCER".equals(type)) {
                workshops.add(new ProducerWorkshop(outputItem, baseTimeRequired));
            }

            if ("MUTATOR".equals(type)) {

                workshops.add(new MutatorWorkshop(outputItem, getItemList(workshop.getJSONArray("inputItemNames"))));
            }
        }

        return workshops;
    }

    private List<Item> getItemList(JSONArray inputItemArray) {
        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i < inputItemArray.length(); ++i) {
            final String itemName = inputItemArray.getString(i);
            items.add(findItemByName(itemName));
        }

        return items;
    }

    private int getBaseTimeRequired(JSONObject workshop) {
        if (workshop.has("baseTimeRequired")) {
            return workshop.getInt("baseTimeRequired");
        }
        return 1;
    }

    private Item findItemByName(String targetItemName) {
        for (Item item : TradesongGame.potentialItems) {
            if (item.getName().equals(targetItemName)) {
                return item;
            }
        }
        throw new IllegalStateException("Dev error! workshops.json referenced item " + targetItemName + " but this wasn't in the item master");
    }
}
