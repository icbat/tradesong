package com.minus7games.tradesong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CraftingStepTest {

    private CraftingStep step;
    protected Item expectedOutput;
    protected Item validInput;
    protected Item invalidInput;

    @Before
    public void setUp() throws Exception {
        validInput = mock(Item.class);
        invalidInput = mock(Item.class);
        expectedOutput = mock(Item.class);
        step = new CraftingStep(validInput, expectedOutput);
    }

    @Test
    public void craft_worksOnValidInput() throws Exception {
        Item output = step.craft(validInput);

        assertEquals("", expectedOutput, output);
    }

    @Test
    public void craft_ignoresInvalidInputs() throws Exception {
        Item output = step.craft(invalidInput);

        assertEquals("", invalidInput, output);
    }
}