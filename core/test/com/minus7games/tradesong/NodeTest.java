package com.minus7games.tradesong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
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
    public void node_addStep_validstep_accepted() throws Exception {
        node.addToWorkflow(validCraftingStep);

        assertTrue(node.getCurrentStepsUnwrapped().contains(validCraftingStep));
    }

    @Test
    public void node_addStep_invalidStep_rejected() throws Exception {
        node.addToWorkflow(invalidCraftingStep);

        assertFalse(node.getCurrentStepsUnwrapped().contains(invalidCraftingStep));
    }
}