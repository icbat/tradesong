package com.minus7games.tradesong.indices;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.minus7games.tradesong.Item;

import java.util.Set;
import java.util.TreeSet;

/***/
public class ItemIndex {
    private static final Set<Item> allTheItems = new TreeSet<Item>();
    public ItemIndex(FileHandle itemsDotJson) {
        Gdx.app.debug("Item Index Init", "reading from file " + itemsDotJson.path());
        JsonReader reader = new JsonReader();
        JsonValue wholeThing = reader.parse(itemsDotJson);
        Gdx.app.debug("nodes found", String.valueOf(wholeThing.size));
        JsonValue itemNode = wholeThing.child();
        Gdx.app.debug("Item Index Init", "reading in items");

        while (itemNode != null) {
            allTheItems.add(Item.parseItem(itemNode));
            itemNode = itemNode.next();
        }
        Gdx.app.log("Item Index Init", "found "+ allTheItems.size()+" unique items");
    }

    public static Item get(String internalName) {
        if (allTheItems.isEmpty()) {
            throw new IllegalStateException("No items have been read!");
        }
        for (Item item : allTheItems) {
            if (item.getInternalName().equalsIgnoreCase(internalName)) {
                return item.copy();
            }
        }
        Gdx.app.error("Item not found with name", internalName);
        throw new IllegalArgumentException("Item not found with name:  " + internalName);
    }

    public static Set<Item> getAllItems() {
        return allTheItems;
    }
}
