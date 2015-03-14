package icbat.games.tradesong.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TurnTakerTest {

    protected TurnTaker turnTaker;
    protected PlayerHoldings holdings;

    @BeforeClass
    public static void setupGdx() {
        Gdx.app = mock(Application.class);
    }

    @Before
    public void setUp() {
        holdings = new PlayerHoldings();
        turnTaker = new TurnTaker(holdings);
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
        assertTrue("storage was touched despite using mocked workshops", holdings.getStorage().isEmpty());
    }

    @Test
    public void generators_addItemsToStorage() {
        holdings.addWorkshop(new ProducerWorkshop(mock(Item.class)));
        assertTrue("test bad, storage started non-empty", holdings.getStorage().isEmpty());

        turnTaker.takeAllTurns();

        assertFalse("storage didn't get an item when it should", holdings.getStorage().isEmpty());
        assertEquals("storage should only have 1 item for 1 producer", 1, holdings.getStorageSize());
    }

    @Test
    public void producers_multiplesAllAddToStorage() {
        holdings.addWorkshop(new ProducerWorkshop(mock(Item.class)));
        holdings.addWorkshop(new ProducerWorkshop(mock(Item.class)));
        holdings.addWorkshop(new ProducerWorkshop(mock(Item.class)));
        assertTrue("test bad, storage started non-empty", holdings.getStorage().isEmpty());

        turnTaker.takeAllTurns();

        assertFalse("storage didn't get an item when it should", holdings.getStorage().isEmpty());
        assertEquals("storage should only have 1 item for 1 producer", 3, holdings.getStorageSize());
    }

    @Test
    public void currentTurn_movesAsExpected() throws Exception {
        assertEquals("current turn not setup right", 1, turnTaker.getCurrentTurn());

        turnTaker.takeAllTurns();
        assertEquals("current turn not incrementing right", 2, turnTaker.getCurrentTurn());

        turnTaker.takeAllTurns();
        assertEquals("current turn not incrementing right", 3, turnTaker.getCurrentTurn());

    }
}