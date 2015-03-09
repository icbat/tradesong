package icbat.games.tradesong.game;

public interface Workshop {
    /**
     * Call to progress the workshop in time, to make it do work
     */
    void takeTurn();

    /**
     * @return true IFF it has an output ready to collect
     */
    boolean hasOutput();

    /**
     * @return the next item output if one exists
     * @throws java.lang.IllegalStateException if there are no outputs to collect; please check with hasOutput first!
     */
    Item getNextOutput();
}
