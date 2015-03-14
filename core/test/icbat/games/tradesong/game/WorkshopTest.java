package icbat.games.tradesong.game;

import icbat.games.tradesong.game.workshops.DelayedWorkshop;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;

public class WorkshopTest {

    DelayedWorkshop workshop;

    @Test
    public void oneTurnToOutput() throws Exception {
        workshop = spy(new TestWorkshop(1));
        Mockito.verify((TestWorkshop) workshop, never()).doWork();

        workshop.takeTurn();

        Mockito.verify((TestWorkshop) workshop).doWork();
    }

    @Test
    public void multipleTurnsToOutput() throws Exception {
        workshop = spy(new TestWorkshop(3));
        Mockito.verify((TestWorkshop) workshop, never()).doWork();

        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop, never()).doWork();
        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop, never()).doWork();

        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop).doWork();
    }

    @Test
    public void multipleTurns_resetsAfterWork() throws Exception {
        workshop = spy(new TestWorkshop(3));
        Mockito.verify((TestWorkshop) workshop, never()).doWork();

        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop, never()).doWork();
        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop, never()).doWork();

        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop).doWork();

        workshop.takeTurn();
        Mockito.verify((TestWorkshop) workshop).doWork();
    }

    private class TestWorkshop extends DelayedWorkshop {
        public TestWorkshop(int delay) {
            this.turnsRequiredForWork = delay;
        }

        @Override
        public void doWork() {

        }

        @Override
        public String getWorkshopName() {
            return null;
        }
    }
}