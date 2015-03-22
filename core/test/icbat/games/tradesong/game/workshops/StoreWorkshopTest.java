package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workers.Worker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class StoreWorkshopTest {

    protected StorefrontWorkshop store;
    protected Item someItem;
    protected PlayerHoldings holdings;

    @Before
    public void setUp() throws Exception {
        holdings = mock(PlayerHoldings.class);
        store = new StorefrontWorkshop(holdings);
        store.getWorkers().addWorker(mock(Worker.class));
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

    @Test
    public void takeTurn_consumesGoods() throws Exception {
        store.sendInput(someItem);
        assertFalse("assumption incorrect, store should be full here for the test to work", store.acceptsInput(someItem));

        store.takeTurn();

        assertTrue("store should now have room but rejected an item", store.acceptsInput(someItem));
    }

    @Test
    public void takeTurn_addsMoneyToHoldings() throws Exception {
        store.sendInput(someItem);
        assertFalse("assumption incorrect, store should be full here for the test to work", store.acceptsInput(someItem));

        store.takeTurn();

        verify(holdings).addCurrency(anyInt());
    }

    @Test
    public void noWorkers_noWork() throws Exception {
        while (store.getWorkers().hasWorkers()) {
            store.getWorkers().removeWorker();
        }
        store.sendInput(someItem);
        assertFalse("assumption incorrect, store should be full here", store.acceptsInput(someItem));

        store.takeTurn();

        assertFalse("No workers should leave the input queue full", store.acceptsInput(someItem));
    }

    @Test
    public void moreWorkers_sellMoreGoods() throws Exception {
        store.getWorkers().addWorker(mock(Worker.class));
        store.getWorkers().addWorker(mock(Worker.class));
        store.updateInputQueueCapacity(3);
        store.sendInput(someItem);
        store.sendInput(someItem);
        store.sendInput(someItem);

        store.takeTurn();

        verify(holdings, times(3)).addCurrency(anyInt());
    }

    @Test
    public void moreWorkers_dontSellNonexistantGoods() throws Exception {
        store.getWorkers().addWorker(mock(Worker.class));
        store.getWorkers().addWorker(mock(Worker.class));
        store.updateInputQueueCapacity(3);
        store.sendInput(someItem);

        store.takeTurn();

        verify(holdings, times(1)).addCurrency(anyInt());

    }
}