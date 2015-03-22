package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workers.Worker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class MutatorWorkshop_WorkingScalingTest {
    @Parameterized.Parameter(0)
    public int workers;
    @Parameterized.Parameter(1)
    public int expectedOutput;
    protected Item goodInput = mock(Item.class);
    protected MutatorWorkshop mutator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0},
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4},
                {5, 5},
        });
    }

    @Test
    public void testCase() throws Exception {
        mutator = new MutatorWorkshop(mock(Item.class), goodInput);

        for (int i = 0; i < workers; ++i) {
            mutator.getWorkers().addWorker(mock(Worker.class));
        }

        final int inputsToSend = 10;
        mutator.setInputQueueCapacity(inputsToSend);
        for (int i = 0; i < inputsToSend; ++i) {
            mutator.sendInput(goodInput);
        }

        mutator.takeTurn();

        assertEquals(expectedOutput, countOutput());
    }

    private int countOutput() {
        int count = 0;
        while (mutator.hasOutput()) {
            mutator.getNextOutput();
            count++;
        }
        return count;
    }
}