package com.minus7games.tradesong;

import com.badlogic.gdx.utils.Json;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SampleJsonGeneration {
    private Json json = new Json();

    @Test
    public void itemCases() throws Exception {
        List<Item> items = new LinkedList<Item>();
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        System.out.println(json.prettyPrint(items));
    }
}