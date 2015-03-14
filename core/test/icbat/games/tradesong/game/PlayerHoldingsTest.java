package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workshops.ItemCreator;
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
    public void creatorsSeparatedOnAdd_happy() throws Exception {
        assertEquals("Workshops not empty to start", 0, holdings.getWorkshops().size());
        assertEquals("Creator list not empty to start", 0, holdings.getItemCreators().size());

        holdings.addWorkshop(mock(ItemCreator.class));

        assertEquals("Creators should still be workshops", 1, holdings.getWorkshops().size());
        assertEquals("creator not added when it should be", 1, holdings.getItemCreators().size());
    }

    @Test
    public void creatorsSeparatedOnAdd_negative() throws Exception {
        assertEquals("Workshops not empty to start", 0, holdings.getWorkshops().size());
        assertEquals("Creator list not empty to start", 0, holdings.getItemCreators().size());

        holdings.addWorkshop(mock(Workshop.class));

        assertEquals("Workshop not added", 1, holdings.getWorkshops().size());
        assertEquals("Generic Workshop was added as a creator", 0, holdings.getItemCreators().size());
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
}