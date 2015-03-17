package icbat.games.tradesong.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ItemEquivalenceTest {
    private final Item item = new Item("itemName");

    @Parameterized.Parameter(0)
    public String nameOfTest;

    @Parameterized.Parameter(1)
    public Boolean expected;

    @Parameterized.Parameter(2)
    public Item otherItem;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"happy path", true, new Item("itemName")},
                {"happy + padding", false, new Item("itemName   ")},
                {"different name", false, new Item("some string")},
                {"empty string name", false, new Item("")},
                {"null name", false, new Item(null)},
                {"null item", false, null},
        });
    }

    @Test
    public void compareItems() throws Exception {
        assertEquals(expected, item.equals(otherItem));

    }
}