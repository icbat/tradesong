package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProducerWorkshopTest {

    protected Item mockItem = mock(Item.class);
    private ProducerWorkshop producer;

    @Before
    public void setUp() throws Exception {
        producer = new ProducerWorkshop(mockItem);
    }

    @Test
    public void hasOutput() throws Exception {
        assertEquals("a new producer had output before its first turn", false, producer.hasOutput());
        producer.takeTurn();
        assertEquals("no output ready after requisite turns", true, producer.hasOutput());
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

    @Test
    public void oneTurnToOutput() throws Exception {
        producer = spy(new ProducerWorkshop(mockItem, 1));
        Mockito.verify(producer, never()).doWork();

        producer.takeTurn();

        Mockito.verify(producer).doWork();
    }

    @Test
    public void multipleTurnsToOutput() throws Exception {
        producer = spy(new ProducerWorkshop(mockItem, 3));
        Mockito.verify(producer, never()).doWork();

        producer.takeTurn();
        Mockito.verify(producer, never()).doWork();
        producer.takeTurn();
        Mockito.verify(producer, never()).doWork();

        producer.takeTurn();
        Mockito.verify(producer).doWork();
    }

    @Test
    public void multipleTurns_resetsAfterWork() throws Exception {
        producer = spy(new ProducerWorkshop(mockItem, 3));
        Mockito.verify(producer, never()).doWork();

        producer.takeTurn();
        Mockito.verify(producer, never()).doWork();
        producer.takeTurn();
        Mockito.verify(producer, never()).doWork();

        producer.takeTurn();
        Mockito.verify(producer).doWork();

        producer.takeTurn();
        Mockito.verify(producer).doWork();
    }
}