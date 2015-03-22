package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class StoreWorkshopTest {

    protected StorefrontWorkshop store;
    protected Item someItem;

    @Before
    public void setUp() throws Exception {
        store = new StorefrontWorkshop();
        someItem = mock(Item.class);
    }

    @Test
    public void acceptsAllInputs() throws Exception {
        assertTrue(store.acceptsInput(someItem));
        store.sendInput(someItem);
    }

    @Test
    public void inputQueueCapacity_limitsInput() throws Exception {
        store.updateInputQueueCapacity(1);
        store.sendInput(someItem);
        assertFalse("Store should no longer accept after capacity is reached", store.acceptsInput(someItem));
        try {
            store.sendInput(someItem);
            fail("Store accepted input when it was unable to do so!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }

    }
}