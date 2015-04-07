package icbat.games.tradesong.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ItemEquivalenceTest {
    private final Item item = new Item("itemName", 100);

    @Parameterized.Parameter(0)
    public String nameOfTest;

    @Parameterized.Parameter(1)
    public Boolean expected;

    @Parameterized.Parameter(2)
    public Item otherItem;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"happy path", true, buildItem("itemName", 100)},
                {"happy - money", false, buildItem("itemName", 99)},
                {"happy + padding", false, buildItem("  itemName   ", 100)},
                {"different name", false, buildItem("some string", 100)},
                {"empty string name", false, buildItem("", 100)},
                {"null name", false, buildItem(null, 100)},
                {"null item", false, null},
        });
    }

    private static Item buildItem(String itemName, int basePrice) {
        return new Item(itemName, basePrice);
    }

    @Test
    public void compareItems() throws Exception {
        assertEquals(expected, item.equals(otherItem));

    }
}