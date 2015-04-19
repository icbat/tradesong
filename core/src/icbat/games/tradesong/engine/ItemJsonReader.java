package icbat.games.tradesong.engine;

import icbat.games.tradesong.game.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/***/
public class ItemJsonReader {
    public Collection<Item> read(String jsonString) {
        JSONArray jsonItems = new JSONArray(jsonString);
        Collection<Item> items = new ArrayList<Item>();
        for (int i=0; i < jsonItems.length(); ++i) {
            JSONObject item = jsonItems.getJSONObject(i);
            final String name = item.getString("name");
            final int basePrice = item.getInt("basePrice");
            items.add(new Item(name, basePrice));
        }
        return items;
    }
}
