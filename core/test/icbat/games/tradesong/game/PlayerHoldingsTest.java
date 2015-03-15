package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workshops.ItemConsumer;
import icbat.games.tradesong.game.workshops.ItemProducer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PlayerHoldingsTest {

    protected PlayerHoldings holdings;

    @Before
    public void setUp() throws Exception {
        holdings = new PlayerHoldings();
    }

    private void verifyHoldingsCleanBeforeWork() {
        assertEquals("Workshops not empty to start", 0, holdings.getWorkshops().size());
        assertEquals("Creator list not empty to start", 0, holdings.getItemCreators().size());
        assertEquals("Consumer list not empty to start", 0, holdings.getItemConsumers().size());
    }

    @Test
    public void creatorAdded() throws Exception {
        verifyHoldingsCleanBeforeWork();

        holdings.addWorkshop(mock(ItemProducer.class));

        assertEquals("Creators should still be workshops", 1, holdings.getWorkshops().size());
        assertEquals("creator not added when it should be", 1, holdings.getItemCreators().size());
        assertEquals("creator was added as a consumer", 0, holdings.getItemConsumers().size());
    }

    @Test
    public void genericWorkshopAdded() throws Exception {
        verifyHoldingsCleanBeforeWork();

        holdings.addWorkshop(mock(Workshop.class));

        assertEquals("Workshop not added", 1, holdings.getWorkshops().size());
        assertEquals("Generic Workshop was added as a creator", 0, holdings.getItemCreators().size());
        assertEquals("creator was added as a consumer", 0, holdings.getItemConsumers().size());
    }

    @Test
    public void consumerAdded() throws Exception {
        verifyHoldingsCleanBeforeWork();

        holdings.addWorkshop(mock(ItemConsumer.class));

        assertEquals("Creators should still be workshops", 1, holdings.getWorkshops().size());
        assertEquals("consumer was added as a creator", 0, holdings.getItemCreators().size());
        assertEquals("consumer not added as a consumer", 1, holdings.getItemConsumers().size());
    }

    @Test
    public void creatorAndConsumerAdded() throws Exception {
        verifyHoldingsCleanBeforeWork();

        holdings.addWorkshop(mock(ProducerAndConsumer.class));

        assertEquals("Creators should still be workshops", 1, holdings.getWorkshops().size());
        assertEquals("creater+consumer not added as a creatorr", 1, holdings.getItemCreators().size());
        assertEquals("creater+consumer not added as a consumer", 1, holdings.getItemConsumers().size());
    }

    @Test
    public void removingSimilar_doesntRemoveAll() throws Exception {
        int expected = 3;
        for (int i = 0; i < expected; ++i) {
            holdings.addWorkshop(mock(Workshop.class));
        }
        assertEquals("Holdings did not get proper workshops added", expected, holdings.getWorkshops().size());

        Workshop workshop = holdings.getWorkshops().get(0);
        holdings.removeWorkshop(workshop);

        assertEquals("Holdings Removed more than it should", expected - 1, holdings.getWorkshops().size());
    }

    @Test
    public void removingConsumers_removesFromBothLists() throws Exception {
        final ItemConsumer consumer = mock(ItemConsumer.class);
        holdings.addWorkshop(consumer);
        holdings.removeWorkshop(consumer);

        assertTrue("didn't remove consumer from basic list", holdings.getWorkshops().isEmpty());
        assertTrue("didn't remove consumer from consumer-only list", holdings.getItemConsumers().isEmpty());
    }

    @Test
    public void removingProducers_removesFromBothLists() throws Exception {
        final ItemProducer consumer = mock(ItemProducer.class);
        holdings.addWorkshop(consumer);
        holdings.removeWorkshop(consumer);

        assertTrue("didn't remove producer from basic list", holdings.getWorkshops().isEmpty());
        assertTrue("didn't remove producer from consumer-only list", holdings.getItemCreators().isEmpty());

    }

    @Test
    public void removeFromStorage_failsIfDoesntExist() throws Exception {
        try {
            holdings.removeFromStorage(mock(Item.class));
            fail("allowed to remove something that doesn't exist");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void removeFromStorage_happyPath() throws Exception {
        final Item item = mock(Item.class);
        holdings.storeItem(item);

        final Item removed = holdings.removeFromStorage(item);

        assertEquals(item, removed);
    }

    private abstract class ProducerAndConsumer implements ItemProducer, ItemConsumer {

    }
}