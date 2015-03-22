package icbat.games.tradesong.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.game.workers.Worker;
import icbat.games.tradesong.game.workshops.ItemConsumer;
import icbat.games.tradesong.game.workshops.ItemProducer;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;
import icbat.games.tradesong.game.workshops.Workshop;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TurnTakerTest {

    protected TurnTaker turnTaker;
    protected PlayerHoldings holdings;
    protected Storage storage;

    @BeforeClass
    public static void setupGdx() {
        Gdx.app = mock(Application.class);
    }

    @Before
    public void setUp() {
        holdings = new PlayerHoldings();
        turnTaker = new TurnTaker(holdings);
        storage = holdings.getStorage();
    }

    @Test
    public void workshops_doWork() {
        final Workshop workshop = mock(Workshop.class);
        holdings.addWorkshop(workshop);
        final Workshop secondShop = mock(Workshop.class);
        holdings.addWorkshop(secondShop);

        turnTaker.takeAllTurns();

        verify(workshop).takeTurn();
        verify(secondShop).takeTurn();
        assertTrue("storage was touched despite using mocked workshops", storage.isEmpty());
    }

    @Test
    public void generators_addItemsToStorage() {
        holdings.addWorkshop(makeProducerWorkshop());
        assertTrue("test bad, storage started non-empty", storage.isEmpty());

        turnTaker.takeAllTurns();

        assertFalse("storage didn't get an item when it should", storage.isEmpty());
        assertEquals("storage should only have 1 item for 1 producer", 1, storage.size());
    }

    private ProducerWorkshop makeProducerWorkshop() {
        final ProducerWorkshop producerWorkshop = new ProducerWorkshop(mock(Item.class));
        producerWorkshop.getWorkers().addWorker(mock(Worker.class));
        return producerWorkshop;
    }

    @Test
    public void producers_multiplesAllAddToStorage() {
        storage.getWorkersAssignedToStorage().addWorker(mock(Worker.class));
        storage.getWorkersAssignedToStorage().addWorker(mock(Worker.class));
        storage.getWorkersAssignedToStorage().addWorker(mock(Worker.class));
        holdings.addWorkshop(makeProducerWorkshop());
        holdings.addWorkshop(makeProducerWorkshop());
        holdings.addWorkshop(makeProducerWorkshop());
        assertTrue("test bad, storage started non-empty", storage.isEmpty());

        turnTaker.takeAllTurns();

        assertFalse("storage didn't get an item when it should", storage.isEmpty());
        assertEquals("storage should only have 1 item for 1 producer", 3, storage.size());
    }

    @Test
    public void currentTurn_movesAsExpected() throws Exception {
        assertEquals("current turn not setup right", 1, turnTaker.getCurrentTurn());

        turnTaker.takeAllTurns();
        assertEquals("current turn not incrementing right", 2, turnTaker.getCurrentTurn());

        turnTaker.takeAllTurns();
        assertEquals("current turn not incrementing right", 3, turnTaker.getCurrentTurn());
    }

    @Test
    public void consumers_getItems() throws Exception {
        final ItemConsumer consumer = mock(ItemConsumer.class);
        acceptAnyInput(consumer);
        holdings.addWorkshop(consumer);
        final Item input = mock(Item.class);
        storage.storeItem(input);

        turnTaker.takeAllTurns();

        verify(consumer).acceptsInput(input);
        verify(consumer).sendInput(input);
    }

    @Test
    public void multipleConsumers_notEnoughIngredients() throws Exception {
        final ItemConsumer luckyConsumer = mock(ItemConsumer.class);
        acceptAnyInput(luckyConsumer);
        holdings.addWorkshop(luckyConsumer);
        final ItemConsumer unluckyConsumer = mock(ItemConsumer.class);
        acceptAnyInput(unluckyConsumer);
        holdings.addWorkshop(unluckyConsumer);
        final Item input = mock(Item.class);
        storage.storeItem(input);

        turnTaker.takeAllTurns();

        verify(luckyConsumer).sendInput(input);
        verify(unluckyConsumer, never()).sendInput(input);
    }

    @Test
    public void consumer_doesntEatAll() throws Exception {
        ItemConsumer consumer = mock(ItemConsumer.class);
        acceptAnyInput(consumer);
        holdings.addWorkshop(consumer);
        storage.storeItem(mock(Item.class));
        storage.storeItem(mock(Item.class));
        storage.storeItem(mock(Item.class));

        turnTaker.takeAllTurns();

        assertFalse("one consumer ate all the items!", storage.isEmpty());

    }

    private void acceptAnyInput(ItemConsumer consumer) {
        when(consumer.acceptsInput(Matchers.<Item>any())).thenReturn(true);
    }

    @Test
    public void noStorageWorkers_producer_doesntMoveOutput() throws Exception {
        while (storage.getWorkersAssignedToStorage().hasWorkers()) {
            storage.getWorkersAssignedToStorage().removeWorker();
        }
        holdings.addWorkshop(makeProducerWorkshop());

        turnTaker.takeAllTurns();

        assertTrue("Nothing should've been moved from the producer!", storage.isEmpty());
    }

    @Test
    public void moreStorageWorkers_producerEmptiesFaster() throws Exception {
        // TODO rename storage.getWorkers...
        storage.getWorkersAssignedToStorage().addWorker(mock(Worker.class));
        final ItemProducer producer = mock(ItemProducer.class);
        when(producer.hasOutput()).thenReturn(true);
        when(producer.getNextOutput()).thenReturn(mock(Item.class));
        holdings.addWorkshop(producer);
        holdings.addWorkshop(producer);

        turnTaker.takeAllTurns();

        assertTrue("More workers moved the same amount of goods", storage.size() > 1);
        assertFalse("Each worker is moving more than they should be able", storage.size() > storage.getWorkersAssignedToStorage().size());
    }
}