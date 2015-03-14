package icbat.games.tradesong.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PlayerHoldingsTest {

    protected PlayerHoldings holdings;

    @Before
    public void setUp() throws Exception {
        holdings = new PlayerHoldings();
    }

    @Test
    public void removingSimilar_doesntRemoveAll() throws Exception {
        int expected = 3;
        for (int i = 0; i < expected; ++i) {
            holdings.addWorkshop(mock(Workshop.class));
        }
        assertEquals("Holdings did not get proper workshops added", expected, holdings.getWorkshopSize());

        Workshop workshop = holdings.getWorkshops().get(0);
        holdings.removeWorkshop(workshop);

        assertEquals("Holdings Removed more than it should", expected - 1, holdings.getWorkshopSize());
    }
}