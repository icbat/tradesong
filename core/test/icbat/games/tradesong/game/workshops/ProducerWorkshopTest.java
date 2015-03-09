package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProducerWorkshopTest {

    private ProducerWorkshop producer;

    @Before
    public void setUp() throws Exception {
        producer = new ProducerWorkshop(new Item("an Item"));
    }

    @Test
    public void hasOutput() throws Exception {
        assertEquals("a new producer had output before its first turn", false, producer.hasOutput());
        producer.takeTurn();
        assertEquals("a new producer had output before its first turn", true, producer.hasOutput());
    }

    @Test
    public void getNextOutput_beforeWork_breaks() throws Exception {
        try {
            producer.getNextOutput();
            fail("workshop didn't throw when access was attempted");
        } catch (IllegalStateException ise) {
            assertTrue("", true);
        }
    }

    @Test
    public void getNextOutput_afterWork_butIsEmpty_breaks() throws Exception {
        producer.takeTurn();
        producer.getNextOutput();

        try {
            producer.getNextOutput();
            fail("workshop didn't throw when access was attempted");
        } catch (IllegalStateException ise) {
            assertTrue("", true);
        }
    }

    @Test
    public void getNextOutput_happyPath() throws Exception {
        producer.takeTurn();
        assertTrue("producer taking turn did nothing", producer.hasOutput());
        final Item output = producer.getNextOutput();

        assertNotNull(output);
    }
}