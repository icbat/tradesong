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
                {"happy path", true, buildItem("itemName")},
                {"happy + padding", false, buildItem("itemName   ")},
                {"different name", false, buildItem("some string")},
                {"empty string name", false, buildItem("")},
                {"null name", false, buildItem(null)},
                {"null item", false, null},
        });
    }

    private static Item buildItem(String itemName) {
        return new Item(itemName);
    }

    @Test
    public void compareItems() throws Exception {
        assertEquals(expected, item.equals(otherItem));

    }
}