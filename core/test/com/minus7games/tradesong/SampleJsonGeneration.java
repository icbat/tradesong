package com.minus7games.tradesong;

import com.badlogic.gdx.utils.Json;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        Item input = new Item("input internal name", "input display name ", "input description");
        Item output = new Item("outputInternalName", "output display name", "output description");

        CraftingStep simpleCraftingStep = new CraftingStep("simple crafting step example", Arrays.asList(input), Arrays.asList(output));
        CraftingStep complexCraftingStep = new CraftingStep("complex crafting step example", Arrays.asList(input, input, input), Arrays.asList(output, output, output));
        CraftingStep producerCraftingStep = new CraftingStep("producer-only crafting step example", new ArrayList<Item>(), Arrays.asList(output));
        Node simpleNode = new Node("nodeWithAllSteps", "some display name", simpleCraftingStep, complexCraftingStep, producerCraftingStep);
        nodes.add(simpleNode);
        nodes.add(new Node("justAProducer", "some display name", producerCraftingStep));
        nodes.add(new Node("justSimpleStep", "some display name", simpleCraftingStep));
        nodes.add(new Node("justComplexStep", "some display name", complexCraftingStep));
        System.out.println(json.prettyPrint(nodes));
    }
}