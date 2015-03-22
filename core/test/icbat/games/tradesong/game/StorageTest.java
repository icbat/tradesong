package icbat.games.tradesong.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class StorageTest {


    private Storage storage;

    @Before
    public void setUp() throws Exception {
        storage = new Storage();
    }

    @Test
    public void removeFromStorage_failsIfDoesntExist() throws Exception {
        try {
            storage.remove(mock(Item.class));
            fail("allowed to remove something that doesn't exist");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void removeFromStorage_happyPath() throws Exception {
        final Item item = mock(Item.class);
        storage.storeItem(item);

        final Item removed = storage.remove(item);

        assertEquals(item, removed);
    }

}