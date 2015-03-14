package icbat.games.tradesong.game;

import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.game.workshops.ItemCreator;

public class TurnTaker {
    private final PlayerHoldings holdings;
    private int currentTurn = 1;

    public TurnTaker(PlayerHoldings holdings) {

        this.holdings = holdings;
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking turn " + currentTurn);
        takeTurnOnAllWorkshops();
        moveAllOutputsToStorage();
        currentTurn++;
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
        Gdx.app.debug("Turn Taker", "After taking turns, storage is now holding " + holdings.getStorage().size());
    }

    private void moveAllOutputsToStorage() {
        for (ItemCreator workshopWithOutput : holdings.getItemCreators()) {
            if (workshopWithOutput.hasOutput()) {
                holdings.storeItem(workshopWithOutput.getNextOutput());
            }
        }
    }

    private void takeTurnOnAllWorkshops() {
        for (Workshop workshop : holdings.getWorkshops()) {
            workshop.takeTurn();
        }
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
