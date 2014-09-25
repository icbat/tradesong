package com.minus7games.tradesong;

import com.badlogic.gdx.utils.Json;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SampleJsonGeneration {
    private Json json = new Json();

    @Test
    public void generateItems() throws Exception {
        List<Item> items = new LinkedList<Item>();
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        items.add(new Item("anInternalName", "A Display Name", "A decent description. Don't be dull!"));
        System.out.println(json.prettyPrint(items));
    }

    @Test
    public void generateNodes() throws Exception {
        List<Node> nodes = new LinkedList<Node>();
        Item output = new Item("intput internal name", "input display name ", "input description");
        Item input = new Item("outputInternalName", "output Dsiplay name", "output description");
        nodes.add(new Node("some internal name", "some display name", new CraftingStep(input, output)));
        nodes.add(new Node("some internal name", "some display name", new CraftingStep(input, output)));
        nodes.add(new Node("some internal name", "some display name", new CraftingStep(input, output)));
        System.out.println(json.prettyPrint(nodes));
    }
}