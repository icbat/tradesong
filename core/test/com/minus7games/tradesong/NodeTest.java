package com.minus7games.tradesong;

import com.minus7games.tradesong.workshops.CraftingStep;
import com.minus7games.tradesong.workshops.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodeTest {
    private Node node;
    private CraftingStep validCraftingStep;
    private CraftingStep invalidCraftingStep;

    @Before
    public void setUp() throws Exception {
        validCraftingStep = mock(CraftingStep.class);
        when(validCraftingStep.getCopy()).thenReturn(validCraftingStep);
        when(validCraftingStep.getDisplayName()).thenReturn("valid step");
        invalidCraftingStep = mock(CraftingStep.class);
        when(invalidCraftingStep.getCopy()).thenReturn(invalidCraftingStep);
        when(validCraftingStep.getDisplayName()).thenReturn("invalid step");
        node = new Node("", "", validCraftingStep);
    }

    @Test
    public void node_addStep_validstep_accepted() throws Exception {
        node.addToWorkflow(validCraftingStep, 0, 0);

        assertTrue(node.getCurrentSteps().contains(validCraftingStep));
    }

    @Test
    public void node_addStep_invalidStep_rejected() throws Exception {
        node.addToWorkflow(invalidCraftingStep, 0, 0);

        assertFalse(node.getCurrentSteps().contains(invalidCraftingStep));
    }
}