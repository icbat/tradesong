package icbat.games.tradesong.game;

import com.badlogic.gdx.Gdx;

public class TurnTaker {
    private final PlayerHoldings holdings;
    private int currentTurn = 1;

    public TurnTaker(PlayerHoldings holdings) {

        this.holdings = holdings;
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking turn " + currentTurn);
        for (Workshop workshop : holdings.getWorkshops()) {
            workshop.takeTurn();
            if (workshop.hasOutput()) {
                final Item output = workshop.getNextOutput();
                holdings.storeItem(output);
            }
        }
        currentTurn++;
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
        Gdx.app.debug("Turn Taker", "After taking turns, storage is now holding " + holdings.getStorageSize());
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
