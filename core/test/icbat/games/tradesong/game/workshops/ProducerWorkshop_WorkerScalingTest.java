package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workers.Worker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class ProducerWorkshop_WorkerScalingTest {

    @Parameterized.Parameter(0)
    public int workers;
    @Parameterized.Parameter(1)
    public int expectedOutput;

    protected ProducerWorkshop producer;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0},
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 3},
                {5, 4},
                {6, 4},
                {7, 4},
                {8, 5},
                {9, 5},
                {10, 5},
                {11, 5},
                {12, 6},
        });
    }

    @Before
    public void setUp() throws Exception {
        producer = new ProducerWorkshop(mock(Item.class));
        producer.updateOutputCapacity(15);
        for (int i = 0; i < workers; ++i) {
            producer.getWorkers().addWorker(mock(Worker.class));
        }
        assertEquals("Workers not set up right", workers, producer.getWorkers().size());
    }

    @Test
    public void testCase() throws Exception {
        producer.takeTurn();

        assertEquals(expectedOutput, countOutput());
    }

    private int countOutput() {
        int count = 0;
        while (producer.hasOutput()) {
            producer.getNextOutput();
            count++;
        }
        return count;
    }
}