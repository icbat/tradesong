package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workers.Worker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProducerWorkshopTest {

    protected Item output = mock(Item.class);
    private ProducerWorkshop producer;

    @Before
    public void setUp() throws Exception {
        final int turnsRequired = 1;
        setupProducer(turnsRequired);
        when(output.spawnClone()).thenReturn(output);
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
        final int turnsRequired = 1;
        setupProducer(turnsRequired);
        Mockito.verify(producer, never()).doWork();

        producer.takeTurn();

        Mockito.verify(producer).doWork();
    }

    @Test
    public void multipleTurnsToOutput() throws Exception {
        final int turnsRequired = 3;
        setupProducer(turnsRequired);
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
        final int turnsRequired = 3;
        setupProducer(turnsRequired);
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

    private void setupProducer(int turnsRequired) {
        producer = spy(new ProducerWorkshop(output, turnsRequired));
        producer.getWorkers().addWorker(mock(Worker.class));
    }

    @Test
    public void outputQueue() throws Exception {
        producer.updateOutputCapacity(1);

        producer.takeTurn();
        producer.takeTurn();
        assertTrue(producer.hasOutput());
        producer.getNextOutput();

        assertFalse(producer.hasOutput());
    }
}