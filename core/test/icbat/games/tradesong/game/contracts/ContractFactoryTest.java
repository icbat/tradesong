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
    public void randomItemContract_usesRequirements() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(anyInt())).thenReturn(0).thenReturn(1);
        holdings.getStorage().getContents().addAll(possibleItems);

        final Contract contract = factory.buildRandomItemContract();

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
    public void randomContract_canBeEitherKind() throws Exception {
        when(random.nextBoolean()).thenReturn(true).thenReturn(false);
        when(random.nextInt(anyInt())).thenReturn(0).thenReturn(1);

        assertTrue(factory.buildRandomContract().viewReward() instanceof ItemReward);
        assertTrue(factory.buildRandomContract().viewReward() instanceof MoneyReward);
    }

    @Test
    public void randomItemContract_cantRewardTheRequirement() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(0).thenReturn(0).thenReturn(1);
        assertTrue("setup inconsistent, test not valid!", holdings.getStorage().isEmpty());

        final Contract contract = factory.buildRandomItemContract();
        contract.viewReward().addRewardToHoldings(holdings);

        final Item rewarded = holdings.getStorage().getContents().get(0);
        assertNotEquals("the same item was the requirement and reward!", possibleItems.get(0), rewarded);
    }

    @Test
    public void randomItemContract_failsIfOnlyOnePossibleItem() throws Exception {
        assertFalse("setup invalid", possibleItems.isEmpty());
        while (possibleItems.size() > 1) {
            possibleItems.remove(0);
        }

        try {
            factory.buildRandomItemContract();
            fail("undefined behavior, contracts need at least one item");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }
}