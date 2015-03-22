package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContractFactoryTest {

    protected ContractFactory factory;
    protected List<Item> possibleItems;
    protected Item requirement;
    protected Item otherRequirement;
    private PlayerHoldings holdings;
    private Random random;

    @Before
    public void setUp() throws Exception {
        possibleItems = new ArrayList<Item>();
        requirement = mock(Item.class);
        possibleItems.add(requirement);
        otherRequirement = mock(Item.class);
        possibleItems.add(otherRequirement);
        holdings = new PlayerHoldings();
        random = mock(Random.class);

        factory = new ContractFactory(possibleItems, random);
    }

    @Test
    public void randomContract_requirementFromSet() throws Exception {
        holdings.getStorage().getContents().addAll(possibleItems);

        final Contract contract = factory.buildRandomContract();

        assertNotNull(contract);
        assertTrue("Should be able to complete", contract.canComplete(holdings));
    }

    @Test
    public void construction_requiresSomeItem() throws Exception {
        try {
            new ContractFactory(new ArrayList<Item>(), random);
            fail("factory requires at least some items!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void randomContract_requiresRandomItems() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(0).thenReturn(1);

        final Contract first = factory.buildRandomContract();
        holdings.getStorage().storeItem(requirement);
        assertTrue(first.canComplete(holdings));

        final Contract second = factory.buildRandomContract();
        assertFalse(second.canComplete(holdings));
        holdings.getStorage().storeItem(otherRequirement);
        assertTrue(second.canComplete(holdings));
    }
}