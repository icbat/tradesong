package com.minus7games.tradesong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class NodeTest {
    private Node node;
    private Item validInput;
    private CraftingStep validCraftingStep;
    private CraftingStep invalidCraftingStep;

    @Before
    public void setUp() throws Exception {
        validInput = mock(Item.class);
        validCraftingStep = mock(CraftingStep.class);
        invalidCraftingStep = mock(CraftingStep.class);
        node = new Node("", "", validCraftingStep);
    }

    @Test
    public void node_addToSection_validstep_accepted() throws Exception {
        node.addToWorkflow(validCraftingStep);

        node.act();

        assertTrue(node.getCurrentSteps().contains(validCraftingStep));
    }
}