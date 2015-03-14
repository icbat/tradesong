package icbat.games.tradesong.game;

public abstract class Workshop {
    /**
     * Set this to higher to do work less often
     */
    protected int turnsRequiredForWork = 1;
    private int turnsTakenSinceLastWork = 0;

    /**
     * Call to progress the workshop in time, to make it do work
     */
    public final void takeTurn() {
        turnsTakenSinceLastWork++;
        if (turnsTakenSinceLastWork >= turnsRequiredForWork) {
            doWork();
            turnsTakenSinceLastWork = 0;
        }
    }

    /**
     * Internal call for when it's time to do work!
     */
    protected abstract void doWork();

    /**
     * @return true IFF it has an output ready to collect
     */
    public abstract boolean hasOutput();

    /**
     * @return the next item output if one exists
     * @throws java.lang.IllegalStateException if there are no outputs to collect; please check with hasOutput first!
     */
    public abstract Item getNextOutput();

    public abstract String getWorkshopName();
}
