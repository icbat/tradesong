package com.minus7games.tradesong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NodeTest {
    private Node node;
    private Item validInput;
    private CraftingStep craftingStep;

    @Before
    public void setUp() throws Exception {
        validInput = mock(Item.class);
        craftingStep = mock(CraftingStep.class);
        node = new Node("", "", craftingStep);
    }

    @Test
    public void node_takesInput() throws Exception {
        node.sendInput(validInput);

        assertFalse(node.getInputCopy().isEmpty());
    }

    @Test
    public void node_withInput_doesWork() throws Exception {
        node.sendInput(validInput);

        node.act();

        verify(craftingStep).send(validInput);
    }
}