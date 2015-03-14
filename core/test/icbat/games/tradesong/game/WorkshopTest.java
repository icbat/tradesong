package icbat.games.tradesong.game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WorkshopTest {

    @Test
    public void oneTurnToOutput() throws Exception {
        Workshop workshop = new TestWorkshop(1);
        assertFalse("had output before work", workshop.hasOutput());

        workshop.takeTurn();

        assertTrue("doesn't have output after correct # of turns", workshop.hasOutput());
    }

    @Test
    public void multipleTurnsToOutput() throws Exception {
        Workshop delayedWorkshop = new TestWorkshop(3);
        assertFalse("had output before work", delayedWorkshop.hasOutput());

        delayedWorkshop.takeTurn();
        assertFalse("has premature output", delayedWorkshop.hasOutput());
        delayedWorkshop.takeTurn();
        assertFalse("has premature output", delayedWorkshop.hasOutput());

        delayedWorkshop.takeTurn();
        assertTrue("doesn't have output after correct # of turns", delayedWorkshop.hasOutput());
    }

    class TestWorkshop extends Workshop {

        protected boolean workWasDone = false;

        public TestWorkshop(int turnsTaken) {
            this.turnsRequiredForWork = turnsTaken;
        }

        @Override
        protected void doWork() {
            workWasDone = true;
        }

        @Override
        public boolean hasOutput() {
            return workWasDone;
        }

        @Override
        public Item getNextOutput() {
            return null;
        }
    }
}