package icbat.games.tradesong.game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TurnTakerTest {

    protected TurnTaker turnTaker;
    protected ArrayList<Workshop> workshops;

    @Before
    public void setUp() throws Exception {
        workshops = new ArrayList<Workshop>();
        turnTaker = new TurnTaker(workshops);
    }

    @Test
    public void workshops_doWork() throws Exception {
        final Workshop workshop = mock(Workshop.class);
        workshops.add(workshop);
        final Workshop secondShop = mock(Workshop.class);
        workshops.add(secondShop);

        turnTaker.takeAllTurns();

        verify(workshop).takeTurn();
        verify(secondShop).takeTurn();
    }
}