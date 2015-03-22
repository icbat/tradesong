package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StoreWorkshopTest {

    protected StorefrontWorkshop store;
    protected Item someItem;
    protected PlayerHoldings holdings;

    @Before
    public void setUp() throws Exception {
        holdings = mock(PlayerHoldings.class);
        store = new StorefrontWorkshop(holdings);
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
}