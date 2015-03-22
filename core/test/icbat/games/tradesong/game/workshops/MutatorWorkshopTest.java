package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.ItemStack;
import icbat.games.tradesong.game.workers.Worker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MutatorWorkshopTest {

    protected final Worker worker = mock(Worker.class);
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
        singleInputMutator.getWorkers().addWorker(worker);
        multipleInputMutator = new MutatorWorkshop(output, goodInput, secondGoodInput);
        multipleInputMutator.getWorkers().addWorker(worker);
        redundantInputMutator = new MutatorWorkshop(output, goodInput, goodInput);
        redundantInputMutator.getWorkers().addWorker(worker);

        when(output.getName()).thenReturn("TestOutput");
        when(output.spawnClone()).thenReturn(output);
        when(goodInput.getName()).thenReturn("Good Input");
        when(secondGoodInput.getName()).thenReturn("A Second Good Input");
        when(badInput.getName()).thenReturn("Bad Input");
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
        assertEquals("at least one input was not present before it was used!", 1, singleInputMutator.getInputStacks().get(0).getSize());
        singleInputMutator.takeTurn();
        assertEquals("at least one input was not present before it was used!", 0, singleInputMutator.getInputStacks().get(0).getSize());
    }

    @Test
    public void multipleInputs_AcceptsAllGood() throws Exception {
        assertTrue("multi input didn't accept first good arg", multipleInputMutator.acceptsInput(goodInput));
        assertTrue("multi input didn't accept second good arg", multipleInputMutator.acceptsInput(secondGoodInput));

        try {
            multipleInputMutator.sendInput(goodInput);
            multipleInputMutator.sendInput(secondGoodInput);
            assertEquals("both inputs not actually accepted, collection size not growing", 2, countTotalItems(multipleInputMutator));
        } catch (IllegalStateException ise) {
            fail("a valid input was not accepted!");
        }
    }

    @Test
    public void multipleInputs_allInputsConsumed() throws Exception {
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(secondGoodInput);
        assertEquals("input size not set up correctly", 2, countTotalItems(multipleInputMutator));

        multipleInputMutator.takeTurn();

        for (ItemStack stack : multipleInputMutator.getInputStacks()) {
            assertEquals(stack.getItem().getName() + " was not removed", 0, stack.getSize());
        }
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
        multipleInputMutator.updateInputQueueCapacity(5);
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(goodInput);
        multipleInputMutator.sendInput(secondGoodInput);
        assertEquals("inputs not set up right, test will be invalid", 4, countTotalItems(multipleInputMutator));

        multipleInputMutator.takeTurn();

        assertTrue("no output after valid work case", multipleInputMutator.hasOutput());
        assertEquals("an incorrect number of inputs was removed", 2, countTotalItems(multipleInputMutator));
    }

    private int countTotalItems(MutatorWorkshop workshop) {
        int count = 0;
        for (ItemStack stack : workshop.getInputStacks()) {
            count += stack.getSize();
        }
        return count;
    }

    @Test
    public void redundantInputs_acceptsTwiceAsMany() throws Exception {
        redundantInputMutator.sendInput(goodInput);
        try {
            redundantInputMutator.sendInput(goodInput);
        } catch (Exception e) {
            fail("Redundant ingredient wasn't accepted!");
        }
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
    public void redundantInputs_removesEnoughInputsWithWork() throws Exception {
        redundantInputMutator.sendInput(goodInput);
        redundantInputMutator.sendInput(goodInput);
        assertEquals("setup conditions were wrong", 2, countTotalItems(redundantInputMutator));

        redundantInputMutator.takeTurn();

        assertEquals("All items weren't removed!", 0, countTotalItems(redundantInputMutator));
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

    @Test
    public void clone_doesntKeepPersonalInfo() throws Exception {
        singleInputMutator.sendInput(goodInput);
        singleInputMutator.getWorkers().addWorker(worker);
        singleInputMutator.takeTurn();
        final MutatorWorkshop singleClone = singleInputMutator.spawnClone();

        assertFalse(singleClone.hasOutput());
        assertFalse(singleClone.getWorkers().hasWorkers());
    }

    @Test
    public void clone_acceptsAllInputs() throws Exception {
        final MutatorWorkshop singleClone = singleInputMutator.spawnClone();
        assertTrue(singleClone.acceptsInput(goodInput));
        assertFalse(singleClone.acceptsInput(secondGoodInput));
        assertFalse(singleClone.acceptsInput(badInput));

        final MutatorWorkshop multiClone = multipleInputMutator.spawnClone();
        assertTrue(multiClone.acceptsInput(goodInput));
        assertTrue(multiClone.acceptsInput(secondGoodInput));
        assertFalse(multiClone.acceptsInput(badInput));

        final MutatorWorkshop redundantClone = redundantInputMutator.spawnClone();
        assertTrue(redundantClone.acceptsInput(goodInput));
        assertFalse(redundantClone.acceptsInput(secondGoodInput));
        assertFalse(redundantClone.acceptsInput(badInput));
    }

    @Test
    public void shouldOnlyAcceptInput_untilItsFull() throws Exception {
        singleInputMutator.sendInput(goodInput);
        assertEquals("setup conditions weren't right", 1, singleInputMutator.getInputStacks().size());

        assertFalse("Workshop will still accept inputs after it is full!", singleInputMutator.acceptsInput(goodInput));
    }

    @Test
    public void differentInputs_allocateToTheirOwnQueues() throws Exception {
        multipleInputMutator.updateInputQueueCapacity(1);
        assertTrue("single input should be allowed even with capacity 1", multipleInputMutator.acceptsInput(goodInput));
        multipleInputMutator.sendInput(goodInput);
        assertFalse("multi input mutator shouldn't be OK with duplicates, should reserve space for all requirements", multipleInputMutator.acceptsInput(goodInput));

        assertTrue("a different input didn't have space allocated for it", multipleInputMutator.acceptsInput(secondGoodInput));
        multipleInputMutator.sendInput(secondGoodInput);
        assertFalse("The different input was allowable after it was full", multipleInputMutator.acceptsInput(secondGoodInput));
    }

    @Test
    public void setQueueCapacity_affectsUnderlyingStacks() throws Exception {
        final int initialCapacity = singleInputMutator.getInputStacks().get(0).getCapacity();

        singleInputMutator.updateInputQueueCapacity(initialCapacity + 7);

        for (ItemStack stack : singleInputMutator.getInputStacks()) {
            assertEquals("underlying stack was not updated with new capacity", initialCapacity + 7, stack.getCapacity());
        }
    }

    @Test
    public void outputQueue() throws Exception {
        singleInputMutator.updateOutputCapacity(1);
        singleInputMutator.sendInput(goodInput);
        singleInputMutator.takeTurn();
        singleInputMutator.sendInput(goodInput);
        singleInputMutator.takeTurn();

        singleInputMutator.getNextOutput();

        assertFalse("Work was done even with a full output queue", singleInputMutator.hasOutput());
    }
}