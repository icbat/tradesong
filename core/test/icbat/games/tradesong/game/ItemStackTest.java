package icbat.games.tradesong.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemStackTest {

    protected final Item goodInput = mock(Item.class);
    protected ItemStack stack;

    @Before
    public void setUp() throws Exception {
        stack = new ItemStack(goodInput, 2);
        when(goodInput.spawnClone()).thenReturn(goodInput);
    }

    @Test
    public void happyPath() throws Exception {
        assertEquals("stack not initialized correctly", 0, stack.getSize());
        stack.addItem(goodInput);
        assertEquals("stack size didn't increment", 1, stack.getSize());
        stack.addItem(goodInput);
        assertEquals("stack size didn't increment", 2, stack.getSize());

        stack.removeItem();
        assertEquals("stack size didn't decrement", 1, stack.getSize());
        stack.removeItem();
        assertEquals("stack size didn't decrement", 0, stack.getSize());
    }

    @Test
    public void doesntThrow_ifIllegalMathAttempted() throws Exception {
        assertEquals("test invalid, assumption was incorrect", 0, stack.getSize());

        try {
            stack.removeItem();
            fail("Stack allowed to decrement when empty!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void itemReturned_isCloneOfSetupItem() throws Exception {
        stack.addItem(goodInput);

        assertTrue("item returned should be the same as the item it was setup with", stack.removeItem().equals(goodInput));
    }

    @Test
    public void addItem_rejectsBadInputs() throws Exception {
        try {
            stack.addItem(mock(Item.class));
            fail("Stack accepted an item other than its prototype item");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void capacity_zero_isUnacceptable() throws Exception {
        try {
            new ItemStack(mock(Item.class), 0);
            fail("Capacity was allowed to be less than one!!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void capacity_negative_isUnacceptable() throws Exception {
        try {
            new ItemStack(mock(Item.class), -2);
            fail("Capacity was allowed to be less than one!!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void capacity_isAbidedBy() throws Exception {
        stack.setCapacity(1);
        stack.addItem(goodInput);
        try {
            stack.addItem(goodInput);
            fail("Item was allowed to be added while at max capacity!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void isFull() throws Exception {
        stack.setCapacity(1);

        assertFalse(stack.isFull());
        stack.addItem(goodInput);
        assertTrue(stack.isFull());
    }
}