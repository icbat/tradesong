package com.minus7games.tradesong;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CraftingStepTest {

    private CraftingStep simpleStep;
    private CraftingStep multipleInputStep;
    private CraftingStep oneInputMultipleOutputs;
    private CraftingStep producer;

    private Item expectedInput;
    private Item inputTwo;
    private Item inputThree;
    private Item invalidInput;

    private Item expectedOutput;

    @Before
    public void setupCases() throws Exception {
        expectedInput = mock(Item.class, "expected/first input");
        inputTwo = mock(Item.class, "second input");
        inputThree = mock(Item.class, "third input");
        invalidInput = mock(Item.class, "INVALID INPUT");

        expectedOutput = mock(Item.class, "expected/first output");
        Item outputTwo = mock(Item.class, "second output");
        Item outputThree = mock(Item.class, "third output");

        simpleStep = new CraftingStep("", Arrays.asList(expectedInput), Arrays.asList(expectedOutput));
        multipleInputStep = new CraftingStep("", Arrays.asList(expectedInput, inputTwo, inputThree), Arrays.asList(expectedOutput));
        oneInputMultipleOutputs = new CraftingStep("", Arrays.asList(expectedInput), Arrays.asList(expectedOutput, outputTwo, outputThree));
        producer = new CraftingStep("", new ArrayList<Item>(), Arrays.asList(expectedOutput));
    }

    @Test
    public void stepReturnsNullWhenEmpty() throws Exception {
        Item output = simpleStep.getNextOutput();

        assertNull(output);
    }

    @Test
    public void step_withValidInput_acceptsIt() throws Exception {
        simpleStep.send(expectedInput);

        Item output = simpleStep.getNextOutput();

        assertNull(output);
    }

    @Test
    public void step_withValidInput_producesAnOutput() throws Exception {
        simpleStep.send(expectedInput);
        simpleStep.act();

        Item output = simpleStep.getNextOutput();

        assertNotNull("Received no output", output);
        assertEquals("Received a different output than expected", expectedOutput, output);
    }

    @Test
    public void step_withoutValidInput_sendItThrough() throws Exception {
        simpleStep.send(invalidInput);
        simpleStep.act();

        Item output = simpleStep.getNextOutput();

        assertNotNull("Received an output when it shouldn't", output);
        assertEquals("", invalidInput, output);
    }

    @Test
    public void step_withValidInput_acceptsFirstButNotSecond() throws Exception {
        simpleStep.send(expectedInput);
        assertNull(simpleStep.getNextOutput());
        simpleStep.send(expectedInput);
        assertEquals("", expectedInput, simpleStep.getNextOutput());
    }

    @Test
    public void multipleInputs_acceptsAll() throws Exception {
        multipleInputStep.send(expectedInput);
        multipleInputStep.send(inputTwo);
        multipleInputStep.send(inputThree);

        Item output = multipleInputStep.getNextOutput();

        assertNull(output);
    }

    @Test
    public void multipleInputs_wontCraftWithoutAll() throws Exception {
        multipleInputStep.send(expectedInput);

        multipleInputStep.act();

        assertNull("Received an output when there should've been none", multipleInputStep.getNextOutput());
    }

    @Test
    public void multipleInputs_CraftsWithAllPresent() throws Exception {
        multipleInputStep.send(expectedInput);
        multipleInputStep.send(inputTwo);
        multipleInputStep.send(inputThree);

        multipleInputStep.act();
        Item output = multipleInputStep.getNextOutput();

        assertNotNull(output);
        assertEquals("", expectedOutput, output);
    }

    @Test
    public void multipleOutputs_craftsMultipleThings() throws Exception {
        oneInputMultipleOutputs.send(expectedInput);

        oneInputMultipleOutputs.act();

        assertNotNull(oneInputMultipleOutputs.getNextOutput());
        assertNotNull(oneInputMultipleOutputs.getNextOutput());
        assertNotNull(oneInputMultipleOutputs.getNextOutput());
        assertNull("Gave back more than expected", oneInputMultipleOutputs.getNextOutput());
    }

    @Test
    public void steps_OutputInOrderOfReceipt() throws Exception {
        simpleStep.send(expectedInput);
        simpleStep.send(inputTwo);
        simpleStep.send(inputThree);
        simpleStep.send(invalidInput);

        assertEquals(inputTwo, simpleStep.getNextOutput());
        assertEquals(inputThree, simpleStep.getNextOutput());
        assertEquals(invalidInput, simpleStep.getNextOutput());
    }

    @Test
    public void producer_outputs_evenWithNoInputs() throws Exception {
        producer.act();

        Item output = producer.getNextOutput();

        assertNotNull("Producer should always have an output after act()",output);
        assertEquals("Wasn't the right output", expectedOutput, output);
    }
}