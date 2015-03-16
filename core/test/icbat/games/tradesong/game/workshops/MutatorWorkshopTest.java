package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workers.Worker;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class MutatorWorkshopTest {

    protected MutatorWorkshop redundantInputMutator;
    private MutatorWorkshop singleInputMutator;
    private MutatorWorkshop multipleInputMutator;
    private Item badInput = mock(Item.class);
    private Item goodInput = mock(Item.class);
    private Item output = mock(Item.class);
    private Item secondGoodInput = mock(Item.class);

    @Before
    public void setUp() throws Exception {
        singleInputMutator = new MutatorWorkshop(output, goodInput);
        singleInputMutator.getWorkers().addWorker(mock(Worker.class));
        multipleInputMutator = new MutatorWorkshop(output, goodInput, secondGoodInput);
        multipleInputMutator.getWorkers().addWorker(mock(Worker.class));
        redundantInputMutator = new MutatorWorkshop(output, goodInput, goodInput);
        redundantInputMutator.getWorkers().addWorker(mock(Worker.class));
    }

    @Test
    public void rejectsBadInput() throws Exception {
        assertFalse(singleInputMutator.acceptsInput(badInput));
        try {
            singleInputMutator.sendInput(badInput);
            fail("mutator accepted bad input!");
        } catch (IllegalStateException ise) {
            assertTrue("", true);
        }
    }

    @Test
    public void getOutput_whenNoWorkDone() throws Exception {
        assertFalse("mutator was given no inputs but still did work", singleInputMutator.hasOutput());
        try {
            singleInputMutator.getNextOutput();
            fail("mutator gave back an item when none existed");
        } catch (IllegalStateException ise) {
            assertTrue("", true);
        }

    }

    @Test
    public void acceptsGoodInput() throws Exception {
        assertTrue(singleInputMutator.acceptsInput(goodInput));
        try {
            singleInputMutator.sendInput(goodInput);
        } catch (IllegalStateException ise) {
            fail("mutator didn't accept good input!");
        }
    }

    @Test
    public void getOutput_whenWorkWasDone() throws Exception {
        singleInputMutator.sendInput(goodInput);

        singleInputMutator.takeTurn();

        assertTrue("given good input, mutator didn't give anything back", singleInputMutator.hasOutput());
        assertEquals("", output, singleInputMutator.getNextOutput());
    }

    @Test
    public void whenWorkIsDone_inputIsConsumed() throws Exception {
        singleInputMutator.sendInput(goodInput);

        singleInputMutator.takeTurn();

        assertTrue("one input was not removed after it was used!", singleInputMutator.getInputQueue().isEmpty());

        singleInputMutator.sendInput(goodInput);
        singleInputMutator.sendInput(goodInput);

        singleInputMutator.takeTurn();

        assertEquals("input size incorrect after work was done", 1, singleInputMutator.getInputQueue().size());
    }

    @Test
    public void multipleInputs_AcceptsAllGood() throws Exception {
        assertTrue("multi input didn't accept first good arg", multipleInputMutator.acceptsInput(goodInput));
        assertTrue("multi input didn't accept second good arg", multipleInputMutator.acceptsInput(secondGoodInput));

        try {
            multipleInputMutator.sendInput(goodInput);
            multipleInputMutator.sendInput(secondGoodInput);
            assertEquals("both inputs not actually accepted, collection size not growing", 2, multipleInputMutator.getInputQueue().size());
        } catch (IllegalStateException ise) {
            fail("a valid input was not accepted!");
        }
    }

    @Test
    public void multipleInputs_allInputsConsumed() throws Exception {
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(secondGoodInput);
        assertEquals("input size not set up correctly", 2, multipleInputMutator.getInputQueue().size());

        multipleInputMutator.takeTurn();

        assertTrue("both inputs not removed", multipleInputMutator.getInputQueue().isEmpty());
    }

    @Test
    public void multipleInputs_doesntWorkWithoutAll() throws Exception {
        multipleInputMutator.takeTurn();
        assertFalse("work was done w/o input!", multipleInputMutator.hasOutput());

        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.takeTurn();
        assertFalse("work was done w/o all inputs!", multipleInputMutator.hasOutput());

        multipleInputMutator.sendInput(secondGoodInput);
        multipleInputMutator.takeTurn();
        assertTrue("work was not done w/ both inputs", multipleInputMutator.hasOutput());
    }

    @Test
    public void multipleInputs_doesntRemoveDuplicatesUnnecessarily() throws Exception {
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(secondGoodInput);
        assertEquals("inputs not set up right, test will be invalid", 4, multipleInputMutator.getInputQueue().size());

        multipleInputMutator.takeTurn();

        assertTrue("no output after valid work case", multipleInputMutator.hasOutput());
        assertEquals("an incorrect number of inputs was removed", 2, multipleInputMutator.getInputQueue().size());
    }

    @Test
    public void redundantInputs_waitsForEnoughInputs() throws Exception {
        redundantInputMutator.sendInput(goodInput);
        redundantInputMutator.takeTurn();
        assertFalse("Work was done w/o enough inputs!", redundantInputMutator.hasOutput());

        redundantInputMutator.sendInput(goodInput);
        redundantInputMutator.takeTurn();
        assertTrue("No work done with enough inputs", redundantInputMutator.hasOutput());
    }

    @Test
    public void multipleInputs_orderDoesntMatter() throws Exception {
        multipleInputMutator.sendInput(secondGoodInput);
        multipleInputMutator.sendInput(goodInput);

        multipleInputMutator.takeTurn();

        assertTrue("no work done on out-of-order ingredients", multipleInputMutator.hasOutput());

    }

    @Test
    public void mutatorDoesntAllowEmptyInputs() throws Exception {
        try {
            new MutatorWorkshop(output);
            fail("mutator with no inputs was allowed to exist!");
        } catch (IllegalStateException ise) {
            assertTrue("", true);
        }
    }
}