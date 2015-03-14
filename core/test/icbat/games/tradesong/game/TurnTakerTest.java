package icbat.games.tradesong.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TurnTakerTest {

    protected TurnTaker turnTaker;
    protected ArrayList<Workshop> workshops;
    protected ArrayList<Item> storage;

    @BeforeClass
    public static void setupGdx() {
        Gdx.app = mock(Application.class);
    }

    @Before
    public void setUp() {
        workshops = new ArrayList<Workshop>();
        storage = new ArrayList<Item>();
        turnTaker = new TurnTaker(workshops, storage);
    }

    @Test
    public void workshops_doWork() {
        final Workshop workshop = mock(Workshop.class);
        workshops.add(workshop);
        final Workshop secondShop = mock(Workshop.class);
        workshops.add(secondShop);

        turnTaker.takeAllTurns();

        verify(workshop).takeTurn();
        verify(secondShop).takeTurn();
        assertTrue("storage was touched despite using mocked workshops", storage.isEmpty());
    }

    @Test
    public void generators_addItemsToStorage() {
        workshops.add(new ProducerWorkshop(mock(Item.class)));
        assertTrue("test bad, storage started non-empty", storage.isEmpty());

        turnTaker.takeAllTurns();

        assertFalse("storage didn't get an item when it should", storage.isEmpty());
        assertEquals("storage should only have 1 item for 1 producer", 1, storage.size());
    }

    @Test
    public void producers_multiplesAllAddToStorage() {
        workshops.add(new ProducerWorkshop(mock(Item.class)));
        workshops.add(new ProducerWorkshop(mock(Item.class)));
        workshops.add(new ProducerWorkshop(mock(Item.class)));
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
}