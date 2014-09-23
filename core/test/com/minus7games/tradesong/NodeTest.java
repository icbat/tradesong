package com.minus7games.tradesong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodeTest {

    private Node node;
    private Item validInput;
    private Item invalidInput;
    protected Item expectedOutput;

    @Before
    public void setUp() throws Exception {
        validInput = mock(Item.class);
        invalidInput = mock(Item.class);
        expectedOutput = mock(Item.class);

        CraftingStep craftingStep = mock(CraftingStep.class);
        when(craftingStep.takesAsInput(validInput)).thenReturn(true);
        when(craftingStep.takesAsInput(invalidInput)).thenReturn(false);

        when(craftingStep.craft(validInput)).thenReturn(expectedOutput);

        node = new Node(craftingStep);
    }

    @Test
    public void node_acceptsInput() throws Exception {
        Item output = node.processesItem(validInput);

        assertEquals("", expectedOutput, output);
    }

    @Test
    public void node_RejectsInvalidInput() throws Exception {
        Item output = node.processesItem(invalidInput);

        assertEquals("", invalidInput, output);
    }
}