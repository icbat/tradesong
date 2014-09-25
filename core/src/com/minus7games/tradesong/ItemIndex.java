package com.minus7games.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.Set;
import java.util.TreeSet;

/***/
public class ItemIndex {
    private final Set<Item> allTheItems = new TreeSet<Item>();
    public ItemIndex(FileHandle itemsDotJson) {
        Gdx.app.debug("Item Index Init", "reading from file " + itemsDotJson.path());
        JsonReader reader = new JsonReader();
        JsonValue wholeThing = reader.parse(itemsDotJson);
        JsonValue itemNode = wholeThing.child();
        Gdx.app.debug("nodes found", String.valueOf(itemNode.size));
        Gdx.app.debug("Item Index Init", "reading in items");

        while (itemNode != null) {
            allTheItems.add(Item.parseItem(itemNode));
            itemNode = itemNode.next();
        }
        Gdx.app.debug("Item Index Init", "found "+ allTheItems.size()+" unique items");

    }
}
