package icbat.games.tradesong.game.workshops;

import icbat.games.tradesong.game.Workshop;

/***/
public abstract class DelayedWorkshop implements Workshop {
    /**
     * Set this to higher to do work less often
     */
    protected int turnsRequiredForWork = 1;
    private int turnsTakenSinceLastWork = 0;

    @Override
    public void takeTurn() {
        turnsTakenSinceLastWork++;
        if (turnsTakenSinceLastWork >= turnsRequiredForWork) {
            doWork();
            turnsTakenSinceLastWork = 0;
        }
    }

    protected abstract void doWork();
}
